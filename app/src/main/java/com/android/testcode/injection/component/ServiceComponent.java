package com.android.testcode.injection.component;

import com.android.testcode.injection.module.ServiceModule;

import dagger.Subcomponent;

@Subcomponent(modules = ServiceModule.class)
public interface ServiceComponent {

}