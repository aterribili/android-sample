package br.com.twitchgames.model;

import org.json.JSONObject;

class TwitchUrlImage {
    private final String large;
    private final String medium;
    private final String small;
    private final String template;

    TwitchUrlImage(String large, String medium, String small, String template) {
        this.large = large;
        this.medium = medium;
        this.small = small;
        this.template = template;
    }

    TwitchUrlImage(JSONObject jsonObject) {
        this(jsonObject.optString("large"), jsonObject.optString("medium"),
                jsonObject.optString("small"), jsonObject.optString("template"));
    }

    String getLarge() {
        return large;
    }

    String getMedium() {
        return medium;
    }

    String getSmall() {
        return small;
    }

    String getTemplate() {
        return template;
    }
}
