package com.example.androiddevchallenge.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionDetector {

    private Context _context;

    public ConnectionDetector(Context context) {
        this._context = context;
    }

    /**
     * Checking for all possible internet providers
     **/
    public boolean isConnectedToInternet() {
        return isConnectedWiFi() || isConnectedMobileNetwork();
    }

    public boolean isConnectedWiFi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }

    public boolean isConnectedMobileNetwork() {
        boolean kResult = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            kResult = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kResult;
    }
}
