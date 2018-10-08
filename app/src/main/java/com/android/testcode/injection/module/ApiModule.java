package com.android.testcode.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = {NetworkModule.class})
public class ApiModule {

}