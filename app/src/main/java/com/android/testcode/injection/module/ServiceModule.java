package com.android.testcode.injection.module;

import android.app.Service;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    private Service service;

    public ServiceModule(Service service) {
        this.service = service;
    }

    @Provides
    Service provideService() {
        return service;
    }

}
