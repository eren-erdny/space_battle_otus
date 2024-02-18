package ru.otus.education.models.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.education.models.command.Command;
import ru.otus.education.models.command.UpdateIoCResolveDependencyStrategy;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Function;

import static ru.otus.education.models.ioc.IoCAction.IOC_UPDATE_RESOLVE_STRATEGY;

class IoCTest {

    /*
     Сделано потому что рандомный порядок у тестов и при запуске может возникнуть ситуация,
     когда у IoC может быть зарегистрирована стратегия из другого тест класса
     */
    @BeforeAll
    @SuppressWarnings("unchecked")
    static void setUp() {
        IoC.strategy = (dependency, args) -> {

            if (IOC_UPDATE_RESOLVE_STRATEGY.equals(dependency)) {
                var updatingStrategy = (Function<BiFunction<String, Object[], Object>, BiFunction<String, Object[], Object>>) args[0];
                return new UpdateIoCResolveDependencyStrategy(updatingStrategy);
            }
            else {
                throw new IllegalArgumentException("Dependency " + dependency + " is not found.");
            }
        };
    }

    @Test
    void whenUpdateStrategy_thenNewStrategyAssigned() {
        // Given
        AtomicBoolean wasCalled = new AtomicBoolean(false);
        Function<BiFunction<String, Object[], Object>, BiFunction<String, Object[], Object>> strategy = (BiFunction<String, Object[], Object> args) -> {
            wasCalled.set(true);
            return args;
        };
        // When
        IoC.<Command>resolve(IOC_UPDATE_RESOLVE_STRATEGY, strategy).execute();
        // Then
        Assertions.assertTrue(wasCalled.get());
    }

    @Test
    void whenResolveUnExisting_thenExceptionThrown() {
        // Given
        var unExistingDependencyName = "Un existing dependency";
        Function<BiFunction<String, Object[], Object>, BiFunction<String, Object[], Object>> strategy =
                (BiFunction<String, Object[], Object> args) -> args;
        // Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> IoC.<Command>resolve(unExistingDependencyName, strategy));
    }

    @Test
    void whenUpdateInvalidStrategy_thenClassCastException() {
        // Then
        Assertions.assertThrows(ClassCastException.class,
                () -> IoC.<String>resolve(IOC_UPDATE_RESOLVE_STRATEGY,
                        (Function<BiFunction<String, Object[], Object>, BiFunction<String, Object[], Object>>) (args) -> args).isEmpty());
    }

}