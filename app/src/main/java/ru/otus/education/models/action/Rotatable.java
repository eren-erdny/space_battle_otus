package ru.otus.education.models.action;

import ru.otus.education.models.util.Angle;

// Идея в том, чтобы разделить окружность на сегменты, имея минимальный угол поворота
public interface Rotatable {
    // Номер направления из directionsNumber
    Angle getAngle();
    // Сколько направлений мы проскакиевам за минимальный угол
    int getAngularVelocity();
    void setAngle(Angle newValue);
}
