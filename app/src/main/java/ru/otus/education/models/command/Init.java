package ru.otus.education.models.command;

import ru.otus.education.models.ioc.DependencyResolverImpl;
import ru.otus.education.models.ioc.IoC;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import static ru.otus.education.models.ioc.IoCAction.*;

@SuppressWarnings("unchecked")
public class Init implements Command {
    static ThreadLocal<Object> currentScope = new ThreadLocal<>();
    private static final ConcurrentMap<String , Function<Object[], Object>> rootScope = new ConcurrentHashMap<>();
    private  static boolean alreadyExecutesSuccessfully = false;

    @Override
    public void execute() {

        if (alreadyExecutesSuccessfully) {
            return;
        }

        rootScope.putIfAbsent(IOC_SET_CURRENT_SCOPE, (args) -> new SetCurrentScope(args[0]));
        rootScope.putIfAbsent(IOC_CLEAR_CURRENT_SCOPE, (args) -> new ClearCurrentScope());
        rootScope.putIfAbsent(IOC_GET_CURRENT_SCOPE, (args) -> currentScope.get() != null ? currentScope.get() : rootScope);
        rootScope.putIfAbsent(IOC_GET_PARENT_SCOPE, (args) -> {
            throw new RuntimeException("Parent scope has no parent scope");
        });
        rootScope.putIfAbsent(IOC_CREATE_EMPTY_SCOPE, (args) -> new ConcurrentHashMap<String , Function<Object[], Object>>());
        rootScope.putIfAbsent(IOC_REGISTER_DEPENDENCY, (args) -> new RegisterDependency((String) args[0], (Function<Object[], Object>) args[1]));
        rootScope.putIfAbsent(
                IOC_CREATE_SCOPE,
                (args) -> {
                    var creatingScope = IoC.<ConcurrentMap<String, Function<Object[], Object>>>resolve(IOC_CREATE_EMPTY_SCOPE);
                    Object parentScope;

                    if (args.length > 0) parentScope = args[0];
                    else parentScope = IoC.resolve(IOC_GET_CURRENT_SCOPE);

                    creatingScope.put(IOC_GET_PARENT_SCOPE, (parentArgs) -> parentScope);
                    return creatingScope;
                });

        IoC.<Command>resolve(
                IOC_UPDATE_RESOLVE_STRATEGY,
                (Function<BiFunction<String, Object[], Object>, BiFunction<String, Object[], Object>>) (newStrategy) ->
                        (String dependency, Object[] args) -> {
                    var scope = currentScope.get() != null ? currentScope.get() : rootScope;
                    var dependencyResolver = new DependencyResolverImpl(scope);
                    return dependencyResolver.resolve(dependency, args);
                })
                .execute();

        alreadyExecutesSuccessfully = true;
    }
}
