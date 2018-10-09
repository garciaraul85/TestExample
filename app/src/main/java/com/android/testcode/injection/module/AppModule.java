package com.android.testcode.injection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.testcode.data.AppDatabase;
import com.android.testcode.injection.ApplicationContext;
import com.android.testcode.util.PreferenceHelper;

import dagger.Module;
import dagger.Provides;


import javax.inject.Singleton;

import static com.android.testcode.util.PreferenceHelper.PREF_FILE_NAME;


@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @ApplicationContext
    SharedPreferences provideSharedPreference(@ApplicationContext Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(@ApplicationContext SharedPreferences sharedPreferences) {
        return new PreferenceHelper(sharedPreferences);
    }

}