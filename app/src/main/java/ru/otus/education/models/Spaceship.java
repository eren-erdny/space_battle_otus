package ru.otus.education.models;

public class Spaceship {
    private Vector velocity;
    private Vector position;
    private Angle angle;
    private Integer angularVelocity;

    public Spaceship(Vector velocity, Vector position, Angle angle, Integer angularVelocity) {
        this.velocity = velocity;
        this.position = position;
        this.angle = angle;
        this.angularVelocity = angularVelocity;
    }

    public Spaceship(Vector velocity, Vector position) {
        this.velocity = velocity;
        this.position = position;
    }

    public Spaceship(Angle angle, Integer angularVelocity) {
        this.angle = angle;
        this.angularVelocity = angularVelocity;
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
}
