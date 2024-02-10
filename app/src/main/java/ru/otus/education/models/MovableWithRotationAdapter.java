package ru.otus.education.models;

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
