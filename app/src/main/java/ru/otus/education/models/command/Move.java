package ru.otus.education.models.command;

import ru.otus.education.models.action.Movable;
import ru.otus.education.models.util.Vector;

public class Move implements Command {

    private final Movable movable;

    public Move(Movable movable) {
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
