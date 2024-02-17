package ru.otus.education.models.command;

import ru.otus.education.models.ioc.IoC;

import java.util.function.BiFunction;
import java.util.function.Function;

public class UpdateIoCResolveDependencyStrategy implements Command {

    private final Function<BiFunction<String, Object[], Object>, BiFunction<String, Object[], Object>> updateIoCStrategy;

    public UpdateIoCResolveDependencyStrategy(
            Function<BiFunction<String, Object[], Object>,
                    BiFunction<String, Object[], Object>> updateIoCStrategy) {
        this.updateIoCStrategy = updateIoCStrategy;
    }

    @Override
    public void execute() {
        IoC.strategy = updateIoCStrategy.apply(IoC.strategy);
    }
}
