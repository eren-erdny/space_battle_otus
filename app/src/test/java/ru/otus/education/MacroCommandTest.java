package ru.otus.education;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.education.models.Spaceship;
import ru.otus.education.models.adapter.FuelableAdapter;
import ru.otus.education.models.adapter.MovableAdapter;
import ru.otus.education.models.adapter.MovableWithRotationAdapter;
import ru.otus.education.models.adapter.RotatableAdapter;
import ru.otus.education.models.command.*;
import ru.otus.education.models.exception.CommandException;
import ru.otus.education.models.util.Angle;
import ru.otus.education.models.util.Vector;

import java.util.List;

public class MacroCommandTest {

    @Test
    void whenMoveWithFuelBurn_thenMovedAndFuelLevelDecreased() {
        // Given
        var expectedPosition = new Vector(4, 1);
        var expectedFuelLevel = 45;
        var position = new Vector(1,1);
        var velocity = new Vector(3,0);
        var fuelLevel = 50;
        var fuelConsumption = 5;
        var spaceship = new Spaceship(position, velocity, fuelLevel, fuelConsumption);
        var moveAdapter = new MovableAdapter(spaceship);
        var fuelAdapter = new FuelableAdapter(spaceship);
        var move = new Move(moveAdapter);
        var checkFuel = new CheckFuel(fuelAdapter);
        var burnFuel = new BurnFuel(fuelAdapter);
        var macroCommand = new MacroCommand(List.of(checkFuel, move, burnFuel));
        // When
        macroCommand.execute();
        // Then
        Assertions.assertAll(
                () -> Assertions.assertTrue(Vector.equal(expectedPosition, moveAdapter.getPosition())),
                () -> Assertions.assertEquals(expectedFuelLevel, fuelAdapter.getFuelLevel())
        );
    }

    @Test
    void whenMoveWithFuelBurn_withFuelLevelInsufficient_thenExceptionThrown() {
        // Given
        var position = new Vector(1,1);
        var velocity = new Vector(3,0);
        var fuelLevel = 5;
        var fuelConsumption = 10;
        var spaceship = new Spaceship(position, velocity, fuelLevel, fuelConsumption);
        var moveAdapter = new MovableAdapter(spaceship);
        var fuelAdapter = new FuelableAdapter(spaceship);
        var move = new Move(moveAdapter);
        var checkFuel = new CheckFuel(fuelAdapter);
        var burnFuel = new BurnFuel(fuelAdapter);
        var macroCommand = new MacroCommand(List.of(checkFuel, move, burnFuel));
        // When
        Assertions.assertThrows(CommandException.class, macroCommand::execute);
    }

    @Test
    void whenMoveWithRotation_thenVelocityChange() {
        // Given
        var position = new Vector(0, 0);
        var velocity = 5;
        var fuelLevel = 50;
        var fuelConsumption = 10;
        var angle = new Angle(0, 8);
        var angularVelocity = 4;
        var spaceship = new Spaceship(
                velocity,
                position,
                angle,
                angularVelocity,
                fuelLevel,
                fuelConsumption);

        var moveAdapter = new MovableWithRotationAdapter(spaceship);
        var rotateAdapter = new RotatableAdapter(spaceship);
        var expectedStartVelocity = new Vector(5, 0);
        var expectedVelocityAfterRotate = new Vector(-5, 0);
        var startPosition = moveAdapter.getPosition();
        var startVelocity = moveAdapter.getVelocity();
        var move = new Move(moveAdapter);
        var rotate = new Rotate(rotateAdapter);
        var macro = new MacroCommand(List.of(rotate, move));
        // When
        macro.execute();
        // Then
        Assertions.assertAll(
                () -> Assertions.assertTrue(
                        Vector.equal(
                                expectedStartVelocity,
                                startVelocity
                        )
                ),
                () -> Assertions.assertTrue(
                        Vector.equal(
                                expectedVelocityAfterRotate,
                                moveAdapter.getVelocity()
                        )
                ),
                () -> Assertions.assertTrue(
                        Vector.equal(
                                Vector.plus(startPosition, moveAdapter.getVelocity()),
                                moveAdapter.getPosition()
                        )
                )
        );
    }

}
