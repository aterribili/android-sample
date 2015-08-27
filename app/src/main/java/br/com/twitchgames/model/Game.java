package br.com.twitchgames.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class Game {
    private final String name;
    private final TwitchUrlImage box;
    private final TwitchUrlImage logo;
    private final List<String> links;
    private final long id;
    private final long giantbombId;

    Game(String name, TwitchUrlImage box, TwitchUrlImage logo, List<String> links, long id, long giantbombId) {
        this.name = name;
        this.box = box;
        this.logo = logo;
        this.links = links;
        this.id = id;
        this.giantbombId = giantbombId;
    }

    public Game(JSONObject jsonObject) {
        this(jsonObject.optString("name"), new TwitchUrlImage(jsonObject.optJSONObject("box")),
                new TwitchUrlImage(jsonObject.optJSONObject("logo")), new ArrayList<String>(),
                jsonObject.optLong("id"), jsonObject.optLong("giantbomb_id"));
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

    List<String> getLinks() {
        return links;
    }

    long getId() {
        return id;
    }

    long getGiantbombId() {
        return giantbombId;
    }
}
