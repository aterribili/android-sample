package br.com.twitchgames.model;

import org.json.JSONObject;

public class TwitchGame {
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

    public int getViewers() {
        return viewers;
    }

    public int getChannels() {
        return channels;
    }
}
