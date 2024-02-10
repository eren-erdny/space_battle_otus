package ru.otus.education.models;

public class MovableAdapter implements Movable {

    private final Spaceship spaceship;

    public MovableAdapter(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public Vector getPosition() {
        return spaceship.getPosition();
    }

    @Override
    public Vector getVelocity() {
        return spaceship.getVelocity();
    }

    @Override
    public void setPosition(Vector newValue) {
        spaceship.setPosition(newValue);
    }
}
