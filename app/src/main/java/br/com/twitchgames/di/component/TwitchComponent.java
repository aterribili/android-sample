package br.com.twitchgames.di.component;

import javax.inject.Singleton;

import br.com.twitchgames.di.module.CoreModule;
import dagger.Component;

@Singleton
@Component(modules = {CoreModule.class})
public interface TwitchComponent {
    void inject(Object object);
}
