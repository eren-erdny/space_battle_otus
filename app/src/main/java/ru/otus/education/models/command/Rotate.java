package ru.otus.education.models.command;

import ru.otus.education.models.action.Rotatable;

public class Rotate implements Command{
    private final Rotatable rotatable;

    public Rotate(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    @Override
    public void execute() {
        rotatable.setAngle(
                rotatable.getAngle().next(rotatable.getAngularVelocity())
        );
    }
}
