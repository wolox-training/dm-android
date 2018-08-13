package ar.com.wolox.android.training.di;

import android.app.Application;

import ar.com.wolox.android.training.TrainingApplication;
import ar.com.wolox.wolmo.core.di.modules.ContextModule;
import ar.com.wolox.wolmo.core.di.modules.DefaultModule;
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope;
import ar.com.wolox.wolmo.networking.di.NetworkingComponent;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@ApplicationScope
@Component(dependencies = {NetworkingComponent.class},
           modules = { AndroidSupportInjectionModule.class, DefaultModule.class,
                   ContextModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<TrainingApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TrainingApplication> {

        @BindsInstance
        public abstract Builder application(Application application);

        @BindsInstance
        public abstract Builder sharedPreferencesName(String sharedPrefName);

        public abstract Builder networkingComponent(NetworkingComponent networkingComponent);

    }
}
