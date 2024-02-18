package ru.otus.education.models.ioc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.education.models.command.Command;
import ru.otus.education.models.command.Init;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static ru.otus.education.models.ioc.IoCAction.*;

public class IoCScopeTest {

    @BeforeEach
    void setUp() {
        new Init().execute();
        var ioCScope = IoC.resolve(IOC_CREATE_SCOPE);
        IoC.<Command>resolve(IOC_SET_CURRENT_SCOPE, ioCScope).execute();
    }

    @AfterEach
    void tearDown() {
        IoC.<Command>resolve(IOC_CLEAR_CURRENT_SCOPE).execute();
    }

    @Test
    void whenRegister_thenResolveDependency() {
        // Given
        Function<Object[], Object> s = (Object[] args) -> (Object) 1;
        // When
        IoC.<Command>resolve(IOC_REGISTER_DEPENDENCY, "someDependency", s).execute();
        // Then
        Assertions.assertEquals(1, IoC.<Integer>resolve("someDependency"));
    }

    @Test
    void whenResolveUnregistered_thenExceptionThrown() {
        // Then
        Assertions.assertThrows(RuntimeException.class, () -> IoC.<Integer>resolve("someDependency"));
    }

    @Test
    @SuppressWarnings("unchecked")
    void whenNotFoundInCurrent_thenShouldResolveInParentScope() {
        // Given
        Function<Object[], Object> someFunction = (Object[] args) -> (Object) 1;
        var expectedDependencyName = "someDependency";
        IoC.<Command>resolve(IOC_REGISTER_DEPENDENCY, expectedDependencyName, someFunction).execute();
        var parentScope = IoC.resolve(IOC_GET_CURRENT_SCOPE);

        var emptyScope = IoC.resolve(IOC_CREATE_SCOPE);
        var emptyScopeActions = (ConcurrentMap<String, Function<Object[], Object>>) emptyScope;
        // When
        IoC.<Command>resolve(IOC_SET_CURRENT_SCOPE, emptyScope).execute();

        Assertions.assertAll(
                () -> Assertions.assertFalse(emptyScopeActions.containsKey(expectedDependencyName)),
                () -> Assertions.assertNotEquals(parentScope, IoC.resolve(IOC_GET_CURRENT_SCOPE)),
                () -> Assertions.assertEquals(emptyScope, IoC.resolve(IOC_GET_CURRENT_SCOPE)),
                () -> Assertions.assertEquals(1, IoC.<Integer>resolve(expectedDependencyName))
        );
    }

    @Test
    void whenParentSetManually_thenResolveFromParent() {
        // Given
        var scope1 = IoC.resolve(IOC_CREATE_SCOPE);
        var scope2 = IoC.resolve(IOC_CREATE_SCOPE, scope1);

        Function<Object[], Object> expectedFunction = (Object[] args) -> (Object)2;
        var expectedDependencyName = "someDependency";

        IoC.resolve(IOC_SET_CURRENT_SCOPE, scope1);
        IoC.<Command>resolve(IOC_REGISTER_DEPENDENCY, expectedDependencyName, expectedFunction).execute();
        IoC.<Command>resolve(IOC_SET_CURRENT_SCOPE, scope2);
        // When
        var res = IoC.<Integer>resolve(expectedDependencyName);
        // Then
        Assertions.assertEquals(2, res);
    }
}
