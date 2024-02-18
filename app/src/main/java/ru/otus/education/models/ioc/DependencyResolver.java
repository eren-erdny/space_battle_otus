package ru.otus.education.models.ioc;

public interface DependencyResolver {
    Object resolve(String dependency, Object...args);
}
