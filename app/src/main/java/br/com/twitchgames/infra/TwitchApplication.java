package br.com.twitchgames.infra;

import android.app.Application;

import br.com.twitchgames.di.component.DaggerTwitchComponent;
import br.com.twitchgames.di.component.TwitchComponent;
import br.com.twitchgames.di.module.CoreModule;

public class TwitchApplication extends Application {
    private TwitchComponent component;
    private static TwitchApplication twitchApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        twitchApplication = this;
        component = DaggerTwitchComponent.builder().coreModule(new CoreModule(this)).build();
    }

    public static TwitchApplication getTwitchApplication() {
        return twitchApplication;
    }

    public TwitchComponent getComponent() {
        return component;
    }
}
