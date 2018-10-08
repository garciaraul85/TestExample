package com.android.testcode.injection.component;

import com.android.testcode.features.RegisterActivity;
import com.android.testcode.injection.PerActivity;
import com.android.testcode.injection.module.ActivityModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RegisterActivity registerActivity);
}