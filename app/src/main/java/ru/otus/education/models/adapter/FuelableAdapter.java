package ru.otus.education.models.adapter;

import ru.otus.education.models.Spaceship;
import ru.otus.education.models.action.Fuelable;

public class FuelableAdapter implements Fuelable {

    private final Spaceship spaceship;

    public FuelableAdapter(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public int getFuelLevel() {
        return spaceship.getFuelLevel();
    }

    @Override
    public void setFuelLevel(int fuelLevel) {
        spaceship.setFuelLevel(fuelLevel);
    }

    @Override
    public int getFuelConsumption() {
        return spaceship.getFuelConsumption();
    }
}
