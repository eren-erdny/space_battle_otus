package ru.otus.education.models.command;

import ru.otus.education.models.exception.CommandException;

import java.util.List;

public class MacroCommand implements Command {

    private final List<Command> commands;

    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
       try {
           commands.forEach(Command::execute);
       } catch (Exception e) {
           throw new CommandException();
       }
    }
}
