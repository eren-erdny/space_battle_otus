package ru.otus.education.models.exception;

import ru.otus.education.models.command.Command;

public interface CommandQueue {
    void push(Command command);
    Command getCommand();
    int size();
    void clear();
}
