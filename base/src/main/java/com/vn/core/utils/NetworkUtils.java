package com.vn.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.vn.core.BaseApplication;

public class NetworkUtils {


    /**
     * Check internet connection
     *
     * @return true if Network is connected.
     */
    public static boolean isNetworkConnected() {
        return isNetworkConnected(BaseApplication.getInstance());
    }


    /**
     * Check internet connection
     *
     * @return true if Network is connected.
     */
    public static boolean isNetworkConnected(@NonNull Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        }
        return false;
    }

}
