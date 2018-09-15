package com.example.vinod.mymodels.WebDemoProject;

import android.app.Application;
import android.content.Context;

import com.example.vinod.mymodels.Database;


/**
 * Created by Vinod on 9/14/2018.
 */

public class MyApplication extends Application {

    // how to use database in activity  example

    private static MyApplication sInstance;
    private static DemoDatabase mDatabase;

    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public synchronized static DemoDatabase getWritableDatabase() {
        if (mDatabase == null) {
            mDatabase = new DemoDatabase(getAppContext());
        }
        return mDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mDatabase = new DemoDatabase(this);
    }
}