package ru.otus.education.models.ioc;

import ru.otus.education.models.command.UpdateIoCResolveDependencyStrategy;

import java.util.function.BiFunction;
import java.util.function.Function;

import static ru.otus.education.models.ioc.IoCAction.IOC_UPDATE_RESOLVE_STRATEGY;

@SuppressWarnings("unchecked")
public class IoC {

    public static BiFunction<String, Object[], Object> strategy = (dependency, args) -> {

        if (IOC_UPDATE_RESOLVE_STRATEGY.equals(dependency)) {
            var updatingStrategy = (Function<BiFunction<String, Object[], Object>, BiFunction<String, Object[], Object>>) args[0];
            return new UpdateIoCResolveDependencyStrategy(updatingStrategy);
        }
        else {
            throw new IllegalArgumentException("Dependency " + dependency + " is not found.");
        }
    };

    public static <T> T resolve (String dependency, Object...args) {
        return (T) strategy.apply(dependency, args);
    }
}
