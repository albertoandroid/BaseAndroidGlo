package com.androiddesdecero.jwtudemy.data.local;

import android.content.Context;

import com.androiddesdecero.jwtudemy.data.local.sharedpreferences.SharedPreferencesManager;
import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.model.Login;

public class LocalDataSource implements RepositoryContract {

    private static LocalDataSource INSTANCE;

    public static LocalDataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }

    private LocalDataSource(){}

    @Override
    public void saveAccessToken(Context context, String token) {
        SharedPreferencesManager.getInstance().saveToken(context, token);
    }

    @Override
    public void getAccessToken(Login login, LoadAccessTokenCallback callback) {
        //No hacer nada por ahora
    }

    @Override
    public void getProfesores(String token, LoadProfesorCallback callback) {
        //Cargados solo en remoto
    }
}
