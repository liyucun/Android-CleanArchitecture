package com.yucun.presentation.di.components;

import com.yucun.presentation.di.PerActivity;
import com.yucun.presentation.di.modules.ActivityModule;
import com.yucun.presentation.di.modules.UserModule;
import com.yucun.presentation.view.fragment.UserListFragment;
import dagger.Component;

/**
 * A scope {@link com.yucun.presentation.di.PerActivity}
 * component.
 * Injects user specific Fragments.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, UserModule.class
}) public interface UserComponent extends ActivityComponent {
  void inject(UserListFragment userListFragment);
}
