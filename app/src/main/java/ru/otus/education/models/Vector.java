package ru.otus.education.models;

public class Vector {

    double x;
    double y;

    public Vector() {
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector plus(Vector vector1, Vector vector2)  {
        Vector result = new Vector();
        result.x = vector1.x + vector2.x;
        result.y = vector1.y + vector2.y;
        return result;
    }

    public static Vector minus(Vector vector1, Vector vector2) {
        Vector result = new Vector();
        result.x = vector1.x + vector2.x;
        result.y = vector1.y + vector2.y;
        return result;
    }

    public static boolean equal(Vector vector1, Vector vector2) {
        return vector1.x == vector2.x && vector1.y == vector2.y;
    }
}
