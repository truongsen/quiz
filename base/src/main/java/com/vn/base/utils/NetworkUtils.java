package com.vn.base.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.vn.base.BaseApplication;

import retrofit2.Response;

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
