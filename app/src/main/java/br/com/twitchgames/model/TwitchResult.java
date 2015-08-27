package br.com.twitchgames.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TwitchResult {
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
        this(null, jsonObject.optInt("_total"));
    }

    public static class TwitchResultBuilder {
        private int total;
        private List<TwitchGame> top;

        public TwitchResultBuilder withTotal(@NonNull int total) {
            this.total = total;

            return this;
        }

        public TwitchResultBuilder withTwitchGames(@Nullable JSONArray jsonArray) {
            ArrayList<TwitchGame> twitchGames = new ArrayList<>();

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    twitchGames.add(new TwitchGame(jsonArray.optJSONObject(i)));
                }
            }

            this.top = twitchGames;

            return this;
        }

        public TwitchResult build() {
            return new TwitchResult(top, total);
        }
    }
}
