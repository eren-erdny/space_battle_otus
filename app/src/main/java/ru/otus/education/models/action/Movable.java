package ru.otus.education.models.action;

import ru.otus.education.models.util.Vector;

public interface Movable {
    Vector getPosition();
    Vector getVelocity();
    void setPosition(Vector newValue);
}
