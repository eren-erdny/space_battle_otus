package ru.otus.education.models.command;

import ru.otus.education.models.action.Fuelable;

public class BurnFuel implements Command {

    private final Fuelable fuelable;

    public BurnFuel(Fuelable fuelable) {
        this.fuelable = fuelable;
    }
    @Override
    public void execute() {
        fuelable.setFuelLevel(
                fuelable.getFuelLevel() - fuelable.getFuelConsumption()
        );
    }
}
