package br.com.twitchgames.repository.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import br.com.twitchgames.model.TwitchResult;

public class TwitchBroadcast {
    private static final String INTENT_FILTER_EXTRA = "twitchResult";
    private static final String INTENT_FILTER = "twitch.games.service";

    private final BroadcastReceiver broadcastReceiver;

    public TwitchBroadcast(final TwitchDelegate delegate, Context context) {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                delegate.success((TwitchResult) intent.getSerializableExtra(INTENT_FILTER_EXTRA));
            }
        };

        getLocalBroadcastManager(context).registerReceiver(broadcastReceiver, new IntentFilter(INTENT_FILTER));
    }

    public void unregister(Context context) {
        getLocalBroadcastManager(context).unregisterReceiver(broadcastReceiver);
    }

    public static void twitchReceived(TwitchResult twitchResult, Context context) {
        Intent intent = new Intent(INTENT_FILTER);
        intent.putExtra(INTENT_FILTER_EXTRA, twitchResult);

        getLocalBroadcastManager(context).sendBroadcast(intent);
    }

    private static LocalBroadcastManager getLocalBroadcastManager(Context context) {
        return LocalBroadcastManager.getInstance(context);
    }
}
