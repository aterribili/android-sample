package br.com.twitchgames.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TwitchResult implements Serializable {
    private final int total;
    private final List<TwitchGame> top;

    TwitchResult(List<TwitchGame> top, int total) {
        this.top = top;
        this.total = total;
    }

    public TwitchResult() {
        this(new ArrayList<TwitchGame>(), 0);
    }

    public TwitchResult(JSONObject jsonObject) {
        this(TwitchGame.toTwitchGames(jsonObject.optJSONArray("top")), jsonObject.optInt("_total"));
    }

    public int getTotal() {
        return total;
    }

    public List<TwitchGame> getTop() {
        return top;
    }
}
