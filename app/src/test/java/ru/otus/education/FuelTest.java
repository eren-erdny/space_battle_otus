package ru.otus.education;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.education.models.command.BurnFuel;
import ru.otus.education.models.command.CheckFuel;
import ru.otus.education.models.exception.InsufficientFuelLevelException;
import ru.otus.education.util.TestUtility;

public class FuelTest {

    @Test
    void whenCheckFuel_thenNothingHappens() {
        // Given
        var command = new CheckFuel(TestUtility.getAdapter(10, 2));
        // When
        Assertions.assertDoesNotThrow(command::execute);
    }

    @Test
    void whenCheckFuel_thenExceptionThrown() {
        // Given
        var command = new CheckFuel(TestUtility.getAdapter(10, 15));
        // When
        Assertions.assertThrows(InsufficientFuelLevelException.class, command::execute);
    }

    @Test
    void whenBurnFuel_thenFuelLevelDecrease() {
        // Given
        var expectedFuelLevel = 8;
        var adapter = TestUtility.getAdapter(10, 2);
        var command = new BurnFuel(adapter);
        // When
        command.execute();
        // Then
        Assertions.assertEquals(expectedFuelLevel, adapter.getFuelLevel());
    }
}
