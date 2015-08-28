package br.com.twitchgames.repository.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TwitchDatabase extends SQLiteOpenHelper {
    private static final String name = "twitch-database";
    private static final int version = 1;
    private static TwitchDatabase database;

    public static String TWITCH_TABLE = "twitch_table";

    public TwitchDatabase(Context context) {
        super(context, name, null, version);
    }

    public static TwitchDatabase getInstance(Context context) {
        if (database == null)
            database = new TwitchDatabase(context);

        return database;
    }

    public static void closeConnection(Context context) {
        getInstance(context).getWritableDatabase().close();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(String.format("CREATE TABLE %s (id INTEGER PRIMARY KEY, json TEXT", TWITCH_TABLE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void clearDatabase(Context context) {
        SQLiteDatabase writableDatabase = getInstance(context).getWritableDatabase();

        writableDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", TWITCH_TABLE));

        onCreate(writableDatabase);
    }
}
