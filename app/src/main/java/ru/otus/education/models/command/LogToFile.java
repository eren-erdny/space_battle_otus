package ru.otus.education.models.command;

import ru.otus.education.models.exception.LogToFileCommandUnableToOpenFileException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static ru.otus.education.models.util.Constants.LOG_FILE_NAME;

public class LogToFile implements Command {
    private final String text;

    public LogToFile(String text) {
        this.text = text;
    }

    @Override
    public void execute() {
        try(var fileWriter = new FileWriter(LOG_FILE_NAME);
            var writer = new BufferedWriter(fileWriter)) {
            writer.append(this.text);
        } catch (IOException exception) {
            throw new LogToFileCommandUnableToOpenFileException();
        }
    }
}
