package com.example.rakshith.cricketapp;

import android.app.Application;

import com.onesignal.OneSignal;
import com.parse.Parse;

/**
 * Created by rakshith on 5/28/16.
 */
public class MyApplication extends Application{
    private static MyApplication ourInstance = new MyApplication();

    public static MyApplication getInstance() {
        return ourInstance;
    }

    public MyApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(getApplicationContext(), "WAuMBKwcjZU6eHZaQMRxVxhLpvkDXHWCP6ODYqMo", "fz1veB4LuwRD65XfND0P6jIVYNsSULbW2XhhEBMw");

        OneSignal.startInit(this).init();
    }
}
