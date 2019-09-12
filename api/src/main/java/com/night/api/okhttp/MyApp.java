package com.night.api.okhttp;

import android.app.Application;


public class MyApp extends Application {
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
