package com.androiddesdecero.jwtudemy.data.local.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static SharedPreferencesManager INSTANCE;

    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String SHARED_PREFERENCES_TOKEN = "SHARED_PREFERENCES_TOKEN";

    public static SharedPreferencesManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SharedPreferencesManager();
        }
        return INSTANCE;
    }

    private SharedPreferences getSharedPreferences(Context applicationContext){
        return applicationContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void saveToken(Context applicationContext, String token){
        SharedPreferences.Editor editor = getSharedPreferences(applicationContext).edit();
        editor.putString(SHARED_PREFERENCES_TOKEN, token);
        editor.commit();
    }

    public String readToken(Context applicationContext){
        return getSharedPreferences(applicationContext).getString(SHARED_PREFERENCES_TOKEN, "");
    }
}
