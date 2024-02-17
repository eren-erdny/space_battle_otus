package ru.otus.education.models.command;

import ru.otus.education.models.action.Fuelable;
import ru.otus.education.models.exception.InsufficientFuelLevelException;

public class CheckFuel implements Command {

    private final Fuelable fuelable;

    public CheckFuel(Fuelable fuelable) {
        this.fuelable = fuelable;
    }

    @Override
    public void execute() {
        if (fuelable.getFuelLevel() < fuelable.getFuelConsumption()) {
            throw new InsufficientFuelLevelException();
        }
    }
}
