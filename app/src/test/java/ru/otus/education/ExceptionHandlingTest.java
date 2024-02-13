package ru.otus.education;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.education.models.command.Command;
import ru.otus.education.models.command.LogToFile;
import ru.otus.education.models.command.Repeat;
import ru.otus.education.models.command.SecondRepeat;
import ru.otus.education.models.exception.CommandQueue;
import ru.otus.education.models.exception.CommandQueueImpl;
import ru.otus.education.models.exception.ExceptionHandler;
import ru.otus.education.util.TestUtility;

import java.util.concurrent.atomic.AtomicBoolean;

public class ExceptionHandlingTest {

    private static final CommandQueue commandQueue = new CommandQueueImpl();

    @AfterEach
    void tearDown() {
        ExceptionHandler.reset();
        commandQueue.clear();
    }

    @BeforeEach
    void setUp() {
        commandQueue.push(new MockCommand());
    }

    @Test
    void whenCommandExecute_thenExceptionHandlerCalled() {
        // Given
        AtomicBoolean isHandlerCalled = new AtomicBoolean(false);
        TestUtility.registerMockCommand(
                (cmd, exc) -> {
                    isHandlerCalled.set(true);
                    return null;
                });
        // When
        TestUtility.runCommands(commandQueue);
        // Then
        Assertions.assertTrue(isHandlerCalled.get());
    }

    @Test
    void whenCommandExecute_thenLogIntoFile() {
        // Given
        TestUtility.registerMockCommand(
                (cmd, ex) -> {
                    var exClassName = ex.getClass().getSimpleName();
                    commandQueue.push(new LogToFile(exClassName));
                    return null;
                });
        // When
        TestUtility.runCommands(commandQueue);
        // Then
        var logContent = TestUtility.getLogFileContent();
        Assertions.assertEquals("MockException", logContent);
    }

    @Test
    void whenCommandExecuteThrows_thenCommandRepeated() {
        // Given
        AtomicBoolean isRepeated = new AtomicBoolean(Boolean.FALSE);
        TestUtility.registerMockCommand(
                (cmd, ex) -> {
                    var repeatCmd = new Repeat(cmd);
                    commandQueue.push(repeatCmd);
                    return null;
                });
        ExceptionHandler.register(
                "Repeat",
                "MockException",
                (cmd, ex) -> {
                    isRepeated.set(Boolean.TRUE);
                   return null;
                });
        // When
        TestUtility.runCommands(commandQueue);
        // Then
        Assertions.assertTrue(isRepeated.get());
    }

    @Test
    void whenCommandExecuted_thenRepeat_thenWriteToLog() {
        // Given
        var expectedLog = "Repeat";
        TestUtility.registerMockCommand(
                (cmd, ex) -> {
                    var repeat = new Repeat(cmd);
                    commandQueue.push(repeat);
                    return repeat;
                });
        ExceptionHandler.register(
                "Repeat",
                "MockException",
                (cmd, ex) -> {
                    commandQueue.push(new LogToFile(cmd.getClass().getSimpleName()));
                    return null;
                });
        // When
        TestUtility.runCommands(commandQueue);
        // Then
        Assertions.assertEquals(expectedLog, TestUtility.getLogFileContent());
    }

    @Test
    void whenCommandExecuted_thenRepeatTwoTimes_thenWriteToLog() {
        // Given
        var expectedLog = "SecondRepeat";
        TestUtility.registerMockCommand(
                (cmd, ex) -> {
                    var repeat = new Repeat(cmd);
                    commandQueue.push(repeat);
                    return repeat;
        });
        ExceptionHandler.register(
                "Repeat",
                "MockException",
                (cmd, ex) -> {
                    var secondRepeat = new SecondRepeat(cmd);
                    commandQueue.push(secondRepeat);
                    return secondRepeat;
        });
        ExceptionHandler.register(
                "SecondRepeat",
                "MockException",
                (cmd, ex) -> {
                    var commandClassName = cmd.getClass().getSimpleName();
                    var logToFile = new LogToFile(commandClassName);
                    commandQueue.push(logToFile);
                    return logToFile;
                }
        );
        // When
        TestUtility.runCommands(commandQueue);
        // Then
        Assertions.assertEquals(expectedLog, TestUtility.getLogFileContent());
    }

    @Test
    void whenCommandExecuted_thenSecondRepeatNotAfterFirst() {
        // Given
        TestUtility.registerMockCommand(
                (cmd, ex) -> {
                    var mockRepeat = new MockRepeatCommand();
                    commandQueue.push(mockRepeat);
                    return mockRepeat;
                }
        );
        ExceptionHandler.register(
                "MockRepeatCommand",
                "MockException",
                (cmd, ex) -> {
                    var secondRepeat = new SecondRepeat(cmd);
                    commandQueue.push(secondRepeat);
                    return secondRepeat;
                }
        );
        // When
        Assertions.assertThrows(RuntimeException.class, () -> TestUtility.runCommands(commandQueue));
    }

    static class MockRepeatCommand implements Command {
        @Override
        public void execute() {
            throw new MockException();
        }
    }

    static class MockCommand implements Command {

        @Override
        public void execute() {
            throw new MockException();
        }
    }

    static class MockException extends RuntimeException {
        public MockException() {
            super();
        }
    }
}
