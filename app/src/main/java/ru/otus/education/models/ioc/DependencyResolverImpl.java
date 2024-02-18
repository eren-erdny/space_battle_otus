package ru.otus.education.models.ioc;

import java.util.Map;
import java.util.function.Function;

import static ru.otus.education.models.ioc.IoCAction.IOC_GET_PARENT_SCOPE;

@SuppressWarnings("unchecked")
public class DependencyResolverImpl implements DependencyResolver {

    private final Map<String, Function<Object[], Object>> dependencies;

    public DependencyResolverImpl(Object scope) {
        this.dependencies = (Map<String, Function<Object[], Object>>) scope;
    }

    @Override
    public Object resolve(String dependency, Object... args) {
        var currDependencies = dependencies;
        while(true) {
            if (currDependencies.containsKey(dependency)) {
                return currDependencies.get(dependency).apply(args);
            }
            else {
                currDependencies = (Map<String, Function<Object[], Object>>) currDependencies.get(IOC_GET_PARENT_SCOPE).apply(args);
            }
        }
    }
}
