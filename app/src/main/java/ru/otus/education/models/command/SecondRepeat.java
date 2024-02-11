package ru.otus.education.models.command;

public class SecondRepeat implements Command {

    private final Command commandToRepeat;

    public SecondRepeat(Command cmd) {
        this.commandToRepeat = cmd;
    }

    @Override
    public void execute() {
        if (!Repeat.class.equals(commandToRepeat.getClass())) {
            throw new RuntimeException("You cannot instantiate second repeat from command other than repeat");
        }
        commandToRepeat.execute();
    }
}
