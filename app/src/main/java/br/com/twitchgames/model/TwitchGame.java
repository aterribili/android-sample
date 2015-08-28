package br.com.twitchgames.model;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TwitchGame implements Serializable{
    private final Game game;
    private final int viewers;
    private final int channels;

    public TwitchGame(Game game, int viewers, int channels) {
        this.game = game;
        this.viewers = viewers;
        this.channels = channels;
    }

    public TwitchGame(JSONObject jsonObject) {
        this(new Game(jsonObject.optJSONObject("game")),
                jsonObject.optInt("viewers"),
                jsonObject.optInt("channels"));
    }

    public Game getGame() {
        return game;
    }

    public String getGameName() {
        return game.getName();
    }

    public String getGameImageUrl() {
        return game.getBox().getMedium();
    }

    public int getViewers() {
        return viewers;
    }

    public int getChannels() {
        return channels;
    }

    static List<TwitchGame> toTwitchGames(@Nullable JSONArray jsonArray) {
        ArrayList<TwitchGame> twitchGames = new ArrayList<>();

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                twitchGames.add(new TwitchGame(jsonArray.optJSONObject(i)));
            }
        }

        return twitchGames;
    }
}
