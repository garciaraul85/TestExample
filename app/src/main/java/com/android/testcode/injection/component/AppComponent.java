package com.android.testcode.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

import com.android.testcode.injection.ApplicationContext;
import com.android.testcode.injection.data.DataManager;
import com.android.testcode.injection.module.AppModule;
import com.google.gson.Gson;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();
}