package ru.otus.education.models;

public interface Movable {
    Vector getPosition();
    Vector getVelocity();
    void setPosition(Vector newValue);
}
