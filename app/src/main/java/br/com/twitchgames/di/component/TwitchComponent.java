package br.com.twitchgames.di.component;

import javax.inject.Singleton;

import br.com.twitchgames.di.module.CoreModule;
import br.com.twitchgames.presentation.TwitchActivity;
import br.com.twitchgames.presentation.adapter.TwitchListAdapter;
import br.com.twitchgames.repository.TwitchRepository;
import dagger.Component;

@Singleton
@Component(modules = {CoreModule.class})
public interface TwitchComponent {
    void inject(TwitchListAdapter twitchListAdapter);
    void inject(TwitchRepository twitchRepository);
    void inject(TwitchActivity twitchActivity);
}
