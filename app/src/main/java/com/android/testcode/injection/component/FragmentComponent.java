package com.android.testcode.injection.component;

import com.android.testcode.injection.PerFragment;
import com.android.testcode.injection.module.FragmentModule;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
}