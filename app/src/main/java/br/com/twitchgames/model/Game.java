package br.com.twitchgames.model;

import org.json.JSONObject;

import java.io.Serializable;

class Game implements Serializable {
    private final String name;
    private final TwitchUrlImage box;
    private final TwitchUrlImage logo;
    private final long id;
    private final long giantbombId;

    Game(String name, TwitchUrlImage box, TwitchUrlImage logo, long id, long giantbombId) {
        this.name = name;
        this.box = box;
        this.logo = logo;
        this.id = id;
        this.giantbombId = giantbombId;
    }

    public Game(JSONObject jsonObject) {
        this(jsonObject.optString("name"), new TwitchUrlImage(jsonObject.optJSONObject("box")),
                new TwitchUrlImage(jsonObject.optJSONObject("logo")),
                jsonObject.optLong("_id"), jsonObject.optLong("giantbomb_id"));
    }

    String getName() {
        return name;
    }

    TwitchUrlImage getBox() {
        return box;
    }

    TwitchUrlImage getLogo() {
        return logo;
    }

    long getId() {
        return id;
    }

    long getGiantbombId() {
        return giantbombId;
    }
}
