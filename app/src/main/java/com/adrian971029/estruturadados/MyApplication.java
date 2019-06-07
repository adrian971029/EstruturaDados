package com.adrian971029.estruturadados;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application {

    private static MyApplication context;
    private static boolean activityVisible;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        Stetho.initializeWithDefaults(this);

    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    public static MyApplication getInstance() {
        return context;
    }

}
