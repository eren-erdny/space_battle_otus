package ru.otus.education.models.util;

import java.util.Arrays;

import static java.lang.Math.min;

public class Vector {
    double [] coordinates;

    public Vector(double...coordinates) {
        this.coordinates = coordinates;
    }

    public static Vector plus(Vector vector1, Vector vector2)  {

        if (vector1 == null || vector2 == null) {
            throw new RuntimeException("Null vector passed");
        }

        if (vector2.coordinates.length != vector1.coordinates.length) {
            throw new RuntimeException("Cannot add vectors with different dimensions");
        }

        return sequentialAddition(vector1.coordinates, vector2.coordinates);
    }

    private static Vector sequentialAddition(double[] array1, double[] array2) {
        var resultCoordinates = new double[min(array1.length, array2.length)];

        if (resultCoordinates.length == 0) {
            throw new RuntimeException("Zero dimension vector passed");
        }
        for (int i = 0; i < array1.length; i++) {
            resultCoordinates[i] = array1[i] + array2[i];
        }
        return new Vector(resultCoordinates);
    }

    public static boolean equal(Vector vector1, Vector vector2) {
        return Arrays.equals(vector1.coordinates, vector2.coordinates);
    }
}
