package ru.otus.education.models.adapter;

import ru.otus.education.models.Spaceship;
import ru.otus.education.models.action.Rotatable;
import ru.otus.education.models.util.Angle;

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
