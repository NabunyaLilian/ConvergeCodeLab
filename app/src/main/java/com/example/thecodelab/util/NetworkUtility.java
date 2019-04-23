package com.example.thecodelab.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtility extends BroadcastReceiver {

    private final NetworkListener networkListener;
    static final String TAG = "NetworkUtility";

    public NetworkUtility(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int[] networks = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};
        if (isConnected(context, networks)) {
            networkListener.withInternet();
        } else {
            networkListener.withNoInternet();
        }

    }

    public boolean isConnected(Context context, int... networks) {
        ConnectivityManager conn =  (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        for (int network : networks) {
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                Log.d(TAG, "isConnected: " + network);
                return true;
            }
        }
        return false;
    }
}
