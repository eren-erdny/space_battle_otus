package ru.otus.education.models.exception;

import ru.otus.education.models.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ExceptionHandler {

    private static final Map<String, Map<String, BiFunction<Command, Exception, Command>>> store = new HashMap<>();

    public static Command handle(Command cmd, Exception e) {
        var commandType = cmd.getClass().getSimpleName();
        var exType = e.getClass().getSimpleName();
        if (store.get(commandType) == null) {
            throw new RuntimeException();
        }
        if (store.get(commandType).get(exType) == null) {
            throw new RuntimeException();
        }

        return store.get(commandType).get(exType).apply(cmd, e);
    }

    public static void register(String commandClass,
                                String exceptionClass,
                                BiFunction<Command, Exception, Command> function) {
        store.computeIfAbsent(commandClass, k -> new HashMap<>());
        var commandMap = store.get(commandClass);
        commandMap.putIfAbsent(exceptionClass, function);
    }

    public static void reset() {
        store.clear();
    }
}
