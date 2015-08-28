package br.com.twitchgames.repository.dao;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.twitchgames.model.TwitchResult;
import br.com.twitchgames.repository.broadcast.TwitchBroadcast;
import br.com.twitchgames.repository.dao.mapper.TwitchResultTableMapper;

public class TwitchDAO {
    private final Context context;

    public TwitchDAO(Context context) {
        this.context = context;
    }

    public void save(@NonNull JSONObject jsonObject) {
        TwitchDatabase twitchDatabase = TwitchDatabase.getInstance(context);

        twitchDatabase.clearDatabase(context);
        twitchDatabase.getWritableDatabase()
                .insert(TwitchDatabase.TWITCH_TABLE, null,
                        new TwitchResultTableMapper(jsonObject).toContentvalues());
    }

    public void getCachedGames() {
        new AsyncTask<Void, Void, TwitchResult>() {
            @Override
            protected TwitchResult doInBackground(Void... params) {
                TwitchDatabase twitchDatabase = TwitchDatabase.getInstance(context);

                Cursor cursor = twitchDatabase.getWritableDatabase()
                        .query(TwitchDatabase.TWITCH_TABLE, new String[]{"json"}, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    try {
                        return new TwitchResult(new JSONObject(cursor.getString(0)));
                    } catch (JSONException e) {
                        Log.e("TwitchGame", e.getMessage());
                    }
                    cursor.close();
                }

                return new TwitchResult();
            }

            @Override
            protected void onPostExecute(TwitchResult twitchResult) {
                TwitchBroadcast.twitchReceived(twitchResult, context);
            }
        }.execute();
    }
}
