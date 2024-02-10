package ru.otus.education.models;

public class RotateCommand implements Command{
    private final Rotatable rotatable;

    public RotateCommand(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    @Override
    public void execute() {
        rotatable.setAngle(
                rotatable.getAngle().next(rotatable.getAngularVelocity())
        );
    }
}
