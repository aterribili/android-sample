package br.com.twitchgames.repository.client.converter.service;

import br.com.twitchgames.model.TwitchResult;
import retrofit.Callback;
import retrofit.http.GET;

public interface TwitchService {
    @GET("/kraken/games/top")
    void getGames(Callback<TwitchResult> callback);
}
