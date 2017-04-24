package com.fernandocejas.android10.sample.presentation.internal.di.components;

import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.internal.di.modules.ActivityModule;
import com.fernandocejas.android10.sample.presentation.internal.di.modules.UserModule;
import dagger.Component;

/**
 * A scope {@link com.fernandocejas.android10.sample.presentation.internal.di.PerActivity}
 * component.
 * Injects user specific Fragments.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, UserModule.class
}) public interface UserComponent extends ActivityComponent {
}
