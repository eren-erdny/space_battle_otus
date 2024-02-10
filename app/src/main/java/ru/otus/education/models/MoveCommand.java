package ru.otus.education.models;

public class MoveCommand implements Command {

    private final Movable movable;

    public MoveCommand(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void execute() {
        movable.setPosition(
                Vector.plus(movable.getPosition(),
                        movable.getVelocity())
        );
    }
}
