package com.example.base;

import android.app.Application;

import okhttp3.OkHttpClient;


public class BaseApplication extends Application {

    private static BaseApplication instance;
//    private static SharedPrefUtils sharedPreferences;

    public BaseApplication() {
        instance = this;
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
//        sharedPreferences = new SharedPrefUtils(getApplicationContext());
        super.onCreate();

    }

    public OkHttpClient buildSSLOkHttpClient() {
        return null;
    }

//    public static SharedPrefUtils getSharedPreferences() {
//        return sharedPreferences;
//    }
}
