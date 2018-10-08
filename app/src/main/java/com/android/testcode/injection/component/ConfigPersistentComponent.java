package com.android.testcode.injection.component;

import com.android.testcode.injection.ConfigPersistent;
import com.android.testcode.injection.module.ActivityModule;
import com.android.testcode.injection.module.FragmentModule;
import com.android.testcode.injection.module.ServiceModule;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity or Fragment but it won't be
 * destroy during configuration changes. Check {@link BaseActivity} and {@link BaseFragment} to see
 * how this components survives configuration changes. Use the {@link ConfigPersistent} scope to
 * annotate dependencies that need to survive configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = AppComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

    ServiceComponent serviceComponent(ServiceModule serviceModule);
}