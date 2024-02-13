package ru.otus.education.models;

public class Angle {

    private static final double TWO_PI = 2 * Math.PI;
    private final int direction;
    private final int directionsNumber;

    public Angle(Integer direction, Integer directionsNumber) {
        this.direction = direction;
        this.directionsNumber = directionsNumber;
    }

    public Angle next(Integer angularVelocity) {
        int direction = (this.direction + angularVelocity) % this.directionsNumber;
        int directionsNumber = this.directionsNumber;
        return new Angle(direction, directionsNumber);
    }

    public Double toDouble() {
        return (double) this.direction / this.directionsNumber * TWO_PI;
    }

    public static boolean equal(Angle angle1, Angle angle2) {
        return angle1.direction == angle2.direction && angle1.directionsNumber == angle2.directionsNumber;
    }

    public Integer getDirectionsNumber() {
        return directionsNumber;
    }
}
