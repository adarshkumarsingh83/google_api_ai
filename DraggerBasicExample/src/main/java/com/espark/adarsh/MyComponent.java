package com.espark.adarsh;

import javax.inject.Singleton;


import dagger.Component;

@Singleton
@Component(modules = { UserModule.class, BackEndServiceModule.class })
public interface MyComponent {

    // provide the dependency for dependent components
    // (not needed for single-component setups)
    BackendService provideBackendService();

    // allow to inject into our Main class
    // method name not important
    void inject(Main main);
}