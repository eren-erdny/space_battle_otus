package ru.otus.education.models.adapter;

import ru.otus.education.models.action.Movable;
import ru.otus.education.models.Spaceship;
import ru.otus.education.models.util.Vector;

public class MovableWithRotationAdapter implements Movable {

    private final Spaceship spaceShip;

    public MovableWithRotationAdapter(Spaceship spaceShip) {
        this.spaceShip = spaceShip;
    }

    @Override
    public Vector getPosition() {
        return spaceShip.getPosition();
    }

    @Override
    public Vector getVelocity() {
        var angle = spaceShip.getAngle();
        var v = spaceShip.getAngularVelocity();
        return new Vector(
                v * Math.cos(angle.toDouble()),
                v * Math.sin(angle.toDouble())
        );
    }

    @Override
    public void setPosition(Vector newValue) {
        spaceShip.setPosition(newValue);
    }
}
