package ru.otus.education.models.adapter;

import ru.otus.education.models.Spaceship;
import ru.otus.education.models.action.Movable;
import ru.otus.education.models.util.Vector;

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
