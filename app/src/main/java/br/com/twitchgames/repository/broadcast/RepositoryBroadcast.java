package br.com.twitchgames.repository.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import br.com.twitchgames.infra.TwitchApplication;
import br.com.twitchgames.model.TwitchResult;

public final class RepositoryBroadcast {
    private static final String INTENT_FILTER_EXTRA = "repository";
    private static final String INTENT_FILTER = "repository.games.service";

    private final BroadcastReceiver broadcastReceiver;

    public RepositoryBroadcast(final TwitchDelegate delegate) {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                delegate.success((TwitchResult) intent.getSerializableExtra(INTENT_FILTER_EXTRA));
            }
        };

        getLocalBroadcastManager().registerReceiver(broadcastReceiver, new IntentFilter(INTENT_FILTER));
    }

    public void unregister() {
        getLocalBroadcastManager().unregisterReceiver(broadcastReceiver);
    }

    public static void twitchReceived(TwitchResult twitchResult) {
        Intent intent = new Intent(INTENT_FILTER);
        intent.putExtra(INTENT_FILTER_EXTRA, twitchResult);

        getLocalBroadcastManager().sendBroadcast(intent);
    }

    private static LocalBroadcastManager getLocalBroadcastManager() {
        return LocalBroadcastManager.getInstance(TwitchApplication.getTwitchApplication());
    }
}
