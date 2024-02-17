package ru.otus.education.models;

import ru.otus.education.models.util.Angle;
import ru.otus.education.models.util.Vector;

public class Spaceship {
    private Vector velocity;
    private Vector position;
    private Angle angle;
    private Integer angularVelocity;

    private Integer fuelLevel;

    private Integer fuelConsumption;

    private Integer velocityModula;

    public Spaceship(Integer velocityModula, Vector position, Angle angle, Integer angularVelocity, Integer fuelLevel, Integer fuelConsumption) {
        this.velocityModula = velocityModula;
        this.position = position;
        this.angle = angle;
        this.angularVelocity = angularVelocity;
        this.fuelLevel = fuelLevel;
        this.fuelConsumption = fuelConsumption;
    }

    public Spaceship(Vector velocity, Vector position) {
        this.velocity = velocity;
        this.position = position;
    }

    public Spaceship(Angle angle, Integer angularVelocity) {
        this.angle = angle;
        this.angularVelocity = angularVelocity;
    }

    public Spaceship(Integer fuelLevel, Integer fuelConsumption) {
        this.fuelLevel = fuelLevel;
        this.fuelConsumption = fuelConsumption;
    }

    public Spaceship(Vector position, Vector velocity, Integer fuelLevel, Integer fuelConsumption) {
        this.position = position;
        this.velocity = velocity;
        this.fuelLevel = fuelLevel;
        this.fuelConsumption = fuelConsumption;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getPosition() {
        return position;
    }

    public Angle getAngle() {
        return angle;
    }

    public Integer getAngularVelocity() {
        return angularVelocity;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    public Integer getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(Integer fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public Integer getVelocityModula() {
        return velocityModula;
    }
}
