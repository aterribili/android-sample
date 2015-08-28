package br.com.twitchgames.repository.client;

import android.content.Context;

import br.com.twitchgames.BuildConfig;
import br.com.twitchgames.infra.TwitchRestAdapter;
import br.com.twitchgames.model.TwitchResult;
import br.com.twitchgames.repository.broadcast.TwitchBroadcast;
import br.com.twitchgames.repository.client.converter.TwitchConverter;
import br.com.twitchgames.repository.client.converter.service.TwitchService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TwitchClient {
    private final Context context;

    public TwitchClient(Context context) {
        this.context = context;
    }

    public void getGames() {
        TwitchService service = new TwitchRestAdapter(BuildConfig.MAIN_URL)
                .create(TwitchService.class, new TwitchConverter(context));

        service.getGames(new Callback<TwitchResult>() {

            @Override
            public void success(TwitchResult twitchResult, Response response) {
                TwitchBroadcast.twitchReceived(twitchResult, context);
            }

            @Override
            public void failure(RetrofitError error) {
                TwitchBroadcast.twitchReceived(new TwitchResult(), context);
            }
        });
    }
}
