package br.com.twitchgames.presentation.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class LayoutTypeBroadcast {
    private static final String INTENT_FILTER_EXTRA = "layout";
    private static final String INTENT_FILTER = "layout.games.service";

    private final BroadcastReceiver broadcastReceiver;

    public LayoutTypeBroadcast(final LayoutDelegate delegate, Context context) {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                delegate.changeLayoutToType((LayoutType) intent.getSerializableExtra(INTENT_FILTER_EXTRA));
            }
        };

        getLocalBroadcastManager(context).registerReceiver(broadcastReceiver, new IntentFilter(INTENT_FILTER));
    }

    public void unregister(Context context) {
        getLocalBroadcastManager(context).unregisterReceiver(broadcastReceiver);
    }

    public static void changeLayoutToType(LayoutType layoutType, Context context) {
        Intent intent = new Intent(INTENT_FILTER);
        intent.putExtra(INTENT_FILTER_EXTRA, layoutType);

        getLocalBroadcastManager(context).sendBroadcast(intent);
    }

    private static LocalBroadcastManager getLocalBroadcastManager(Context context) {
        return LocalBroadcastManager.getInstance(context);
    }
}
