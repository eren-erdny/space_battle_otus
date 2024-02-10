package ru.otus.education;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.otus.education.models.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class RotateTest {

    private final Rotatable rotatableMock = mock(Rotatable.class);

    @ParameterizedTest
    @ValueSource(ints = {2, 10})
    void rotateTest(int velocity) {
        // Given
        var expectedAngle = new Angle(3, 8);
        var angle = new Angle(1, 8);
        var spaceship = new Spaceship(angle, velocity);
        var rotatableAdapter = new RotatableAdapter(spaceship);
        var rotateCommand = new RotateCommand(rotatableAdapter);
        // When
        rotateCommand.execute();
        // Then
        Assertions.assertTrue(Angle.equal(expectedAngle, rotatableAdapter.getAngle()));
    }

    @Test
    void whenGetAngularVelocityThrows_thenExceptionThrown() {
        // Given
        Mockito.when(rotatableMock.getAngularVelocity()).thenThrow(RuntimeException.class);
        // When
        var rotateCommand = new RotateCommand(rotatableMock);
        // Then
        Assertions.assertThrows(RuntimeException.class, rotateCommand::execute);
    }

    @Test
    void whenGetAngleThrows_thenExceptionThrown() {
        // Given
        Mockito.when(rotatableMock.getAngle()).thenThrow(RuntimeException.class);
        // When
        var rotateCommand = new RotateCommand(rotatableMock);
        // Then
        Assertions.assertThrows(RuntimeException.class, rotateCommand::execute);
    }

    @Test
    void whenSetAngleThrows_thenExceptionThrown() {
        // Given
        Mockito.doThrow(RuntimeException.class).when(rotatableMock).setAngle(any(Angle.class));
        // When
        var rotateCommand = new RotateCommand(rotatableMock);
        // Then
        Assertions.assertThrows(RuntimeException.class, rotateCommand::execute);
    }

    @Test
    void whenSetAngleWithZeroDirectionsNumber_thenExceptionThrown() {
        // Given
        var angle = new Angle(5, 0);
        var angularVelocity = 1;
        var spaceship = new Spaceship(angle, angularVelocity);
        var rotatableAdapter = new RotatableAdapter(spaceship);
        // When
        var rotateCommand = new RotateCommand(rotatableAdapter);
        // Then
        Assertions.assertThrows(RuntimeException.class, rotateCommand::execute);
    }
}
