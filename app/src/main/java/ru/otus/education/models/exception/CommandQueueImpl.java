package ru.otus.education.models.exception;

import ru.otus.education.models.command.Command;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CommandQueueImpl implements CommandQueue {

    Queue<Command> commandQueue = new ConcurrentLinkedQueue<>();

    public CommandQueueImpl() {}

    @Override
    public void push(Command command) {
        commandQueue.add(command);
    }

    @Override
    public Command getCommand() {
        if (commandQueue.isEmpty()) {
            throw new RuntimeException();
        }
        return commandQueue.remove();
    }

    @Override
    public int size() {
        return commandQueue.size();
    }

    @Override
    public void clear() {
        commandQueue.clear();
    }
}
