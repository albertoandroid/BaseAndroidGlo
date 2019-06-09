package com.androiddesdecero.jwtudemy;

import android.app.Application;
import android.content.Context;

import com.androiddesdecero.jwtudemy.utils.AndroidContextHelper;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidContextHelper.currentContext = getApplicationContext();
    }
}
