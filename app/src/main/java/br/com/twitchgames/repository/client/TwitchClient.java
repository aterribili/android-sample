package br.com.twitchgames.repository.client;

import br.com.twitchgames.BuildConfig;
import br.com.twitchgames.infra.TwitchRestAdapter;
import br.com.twitchgames.model.TwitchResult;
import br.com.twitchgames.repository.client.converter.TwitchConverter;
import br.com.twitchgames.repository.service.TwitchService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TwitchClient {
    void getGames() {
        TwitchService service = new TwitchRestAdapter(BuildConfig.MAIN_URL)
                .create(TwitchService.class, new TwitchConverter());

        service.getGames(new Callback<TwitchResult>() {

            @Override
            public void success(TwitchResult twitchResult, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

}
