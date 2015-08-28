package br.com.twitchgames.repository.dao.mapper;

import android.content.ContentValues;

import org.json.JSONObject;

public class TwitchResultTableMapper {
    private final JSONObject jsonObject;

    public TwitchResultTableMapper(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public ContentValues toContentvalues(){
        ContentValues values = new ContentValues();
        values.put("json", jsonObject.toString());

        return values;
    }
}
