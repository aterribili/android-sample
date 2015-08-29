package br.com.twitchgames.di.module;

import android.content.Context;

import br.com.twitchgames.infra.TwitchApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class CoreModule {
    private final TwitchApplication application;

    public CoreModule(TwitchApplication application) {
        this.application = application;
    }

    @Provides
    public Context providesContext() {
        return application;
    }
}
