package com.androidatc.materialdesign;

import android.app.Application;
import android.content.Context;

/**
 * Created by jorgecasariego on 1/9/15.
 */
public class MyApplication extends Application {
    public static final String API_KEY_ROTTEN_TOMATOES = "54wzfswsa4qmjg8hjwa64d4c";
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }
}
