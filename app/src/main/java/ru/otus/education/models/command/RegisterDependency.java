package ru.otus.education.models.command;

import ru.otus.education.models.ioc.IoC;

import java.util.Map;
import java.util.function.Function;

public class RegisterDependency implements Command {

    private final String dependency;
    private final Function<Object[], Object> dependencyResolverStrategy;

    public RegisterDependency(String dependency, Function<Object[], Object> dependencyResolverStrategy) {
        this.dependency = dependency;
        this.dependencyResolverStrategy = dependencyResolverStrategy;
    }

    @Override
    public void execute() {
        var currentScope = IoC.<Map<String, Function<Object[], Object>>>resolve("IoC.Scope.Current");
        currentScope.put(dependency, dependencyResolverStrategy);
    }
}
