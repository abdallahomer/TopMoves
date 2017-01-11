package com.example.prog_abdallah.topmoves;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ProG_AbdALlAh on 1/11/2017.
 */

public class CheckInternet {
    public static boolean isOnline(Context context) {
        ConnectivityManager online = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netIn = online.getActiveNetworkInfo();

        if (netIn != null && netIn.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
