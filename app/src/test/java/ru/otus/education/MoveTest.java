package ru.otus.education;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.education.models.Spaceship;
import ru.otus.education.models.action.Movable;
import ru.otus.education.models.adapter.MovableAdapter;
import ru.otus.education.models.command.Move;
import ru.otus.education.models.util.Vector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class MoveTest {

    Movable movableAdapterMock = mock(Movable.class);

    @Test
    void moveTest() {
        // Given
        var initPosition = new Vector(12, 5);
        var initVelocity = new Vector(-7, 3);
        var expectedPosition = new Vector(5, 8);
        var spaceship = new Spaceship(initPosition, initVelocity);
        var movableAdapter = new MovableAdapter(spaceship);
        var moveCommand = new Move(movableAdapter);
        // When
        moveCommand.execute();
        // Then
        Assertions.assertTrue(Vector.equal(expectedPosition, movableAdapter.getPosition()));
    }

    @Test
    void whenGetPositionThrows_thenExceptionThrown() {
        // Given
        Mockito.when(movableAdapterMock.getPosition()).thenThrow(RuntimeException.class);
        // When
        var moveCommand = new Move(movableAdapterMock);
        // Then
        Assertions.assertThrows(RuntimeException.class, moveCommand::execute);
    }

    @Test
    void whenGetVelocityThrows_thenExceptionThrown() {
        // Given
        Mockito.when(movableAdapterMock.getVelocity()).thenThrow(RuntimeException.class);
        // When
        var moveCommand = new Move(movableAdapterMock);
        // Then
        Assertions.assertThrows(RuntimeException.class, moveCommand::execute);
    }

    @Test
    void whenSetPositionThrows_thenExceptionThrown() {
        // Given
        Mockito.doThrow(RuntimeException.class).when(movableAdapterMock).setPosition(any(Vector.class));
        // When
        var moveCommand = new Move(movableAdapterMock);
        // Then
        Assertions.assertThrows(RuntimeException.class, moveCommand::execute);
    }
}
