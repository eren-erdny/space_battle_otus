package ru.otus.education.models;

public class RotatableAdapter implements Rotatable {

    Spaceship spaceship;

    public RotatableAdapter(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public Angle getAngle() {
        var res = spaceship.getAngle();
        if (res.getDirectionsNumber() == 0) {
            throw new RuntimeException("Directions number must not be zero");
        }
        return res;
    }

    @Override
    public int getAngularVelocity() {
        return spaceship.getAngularVelocity();
    }

    @Override
    public void setAngle(Angle newValue) {
        spaceship.setAngle(newValue);
    }
}
