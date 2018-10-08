package com.android.testcode;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.testcode.injection.component.AppComponent;
import com.android.testcode.injection.component.DaggerAppComponent;
import com.android.testcode.injection.module.AppModule;
import com.android.testcode.injection.module.NetworkModule;

import static com.android.testcode.util.PreferenceHelper.PREF_FILE_NAME;

public class MVVMApplication extends Application {

    private AppComponent appComponent;

    public static MVVMApplication get(Context context) {
        return (MVVMApplication) context.getApplicationContext();
    }

    public AppComponent getComponent() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        String apiUrl = "";


        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule(this, apiUrl))
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

}