package ru.otus.education.util;

import ru.otus.education.models.Spaceship;
import ru.otus.education.models.action.Fuelable;
import ru.otus.education.models.adapter.FuelableAdapter;
import ru.otus.education.models.command.Command;
import ru.otus.education.models.exception.CommandQueue;
import ru.otus.education.models.exception.ExceptionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.BiFunction;

import static ru.otus.education.models.util.Constants.LOG_FILE_NAME;

public class TestUtility {

    public static void runCommands(CommandQueue commands) {
        while (commands.size() > 0) {
            var cmd = commands.getCommand();
            try {
                cmd.execute();
            } catch (Exception e) {
                ExceptionHandler.handle(cmd, e);
            }
        }
    }

    public static String getLogFileContent() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE_NAME))) {
            return bufferedReader.readLine();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void registerMockCommand(BiFunction<Command, Exception, Command> function) {
        ExceptionHandler.register(
                "MockCommand",
                "MockException",
                function);
    }

    public static Fuelable getAdapter(int fuelLevel, int fuelConsumption) {
        var spaceship = new Spaceship(fuelLevel, fuelConsumption);
        return new FuelableAdapter(spaceship);
    }

}
