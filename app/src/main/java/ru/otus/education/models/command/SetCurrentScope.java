package ru.otus.education.models.command;

public class SetCurrentScope implements Command {

    private final Object scope;

    public SetCurrentScope(Object scope) {
        this.scope = scope;
    }

    @Override
    public void execute() {
        Init.currentScope.set(scope);
    }
}
