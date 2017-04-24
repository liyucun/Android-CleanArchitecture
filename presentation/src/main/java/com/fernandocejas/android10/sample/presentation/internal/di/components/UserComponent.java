package com.fernandocejas.android10.sample.presentation.internal.di.components;

import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.internal.di.modules.ActivityModule;
import com.fernandocejas.android10.sample.presentation.internal.di.modules.UserModule;
import com.fernandocejas.android10.sample.presentation.view.fragment.UserListFragment;
import dagger.Component;

/**
 * A scope {@link com.fernandocejas.android10.sample.presentation.internal.di.PerActivity}
 * component.
 * Injects user specific Fragments.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, UserModule.class
}) public interface UserComponent extends ActivityComponent {
  void inject(UserListFragment userListFragment);
}
