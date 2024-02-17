package ru.otus.education.models.ioc;

public interface IoCAction {
    String IOC_SET_CURRENT_SCOPE = "IoC.Scope.Current.Set";
    String IOC_CLEAR_CURRENT_SCOPE = "IoC.Scope.Current.Clear";
    String IOC_GET_CURRENT_SCOPE = "IoC.Scope.Current";
    String IOC_GET_PARENT_SCOPE = "IoC.Scope.Parent";
    String IOC_CREATE_EMPTY_SCOPE = "IoC.Scope.Create.Empty";
    String IOC_REGISTER_DEPENDENCY = "IoC.Register";
    String IOC_CREATE_SCOPE = "IoC.Scope.Create";
    String IOC_UPDATE_RESOLVE_STRATEGY = "Update IoC Resolve Dependency Strategy";
}
