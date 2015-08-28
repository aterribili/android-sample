package br.com.twitchgames.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connection {
    private Connection(){}

    public static boolean isAvailable(Context context) {
        NetworkInfo networkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        if (networkInfo == null)
            return false;

        if (!networkInfo.isConnected())
            return false;

        if (!networkInfo.isAvailable())
            return false;

        return true;
    }

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
