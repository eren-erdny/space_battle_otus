package ru.otus.education.models.command;

public class ClearCurrentScope implements Command {
    @Override
    public void execute() {
        Init.currentScope.remove();
    }
}
