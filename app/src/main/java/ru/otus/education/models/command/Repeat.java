package ru.otus.education.models.command;

public class Repeat implements Command {

    private final Command commandToRepeat;

    public Repeat(Command commandToRepeat) {
        this.commandToRepeat = commandToRepeat;
    }
    @Override
    public void execute() {
        commandToRepeat.execute();
    }
}
