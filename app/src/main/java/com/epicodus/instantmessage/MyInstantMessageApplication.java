package com.epicodus.instantmessage;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by jakemento on 5/4/16.
 */
public class MyInstantMessageApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}