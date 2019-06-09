package com.androiddesdecero.jwtudemy.data;

import android.content.Context;
import android.util.Log;

import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.domain.model.DomainError;
import com.androiddesdecero.jwtudemy.model.Login;
import com.androiddesdecero.jwtudemy.model.ProfesorEntity;

import java.util.List;

public class Repository implements RepositoryContract {

    private static Repository INSTANCE;
    private RepositoryContract localDataSource;
    private RepositoryContract remoteDataSource;
    private RepositoryContract roomDataSource;

    public static Repository newInstance(RepositoryContract localDataSource,
                                         RepositoryContract remoteDataSource,
                                         RepositoryContract roomDataSource) {
        INSTANCE = new Repository(localDataSource, remoteDataSource, roomDataSource);
        return INSTANCE;
    }

    private Repository(RepositoryContract localDataSource, RepositoryContract remoteDataSource, RepositoryContract roomDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.roomDataSource = roomDataSource;
    }

    @Override
    public void saveAccessToken(Context context, String token) {
        if(localDataSource != null){
            localDataSource.saveAccessToken(context, token);
        }
    }

    @Override
    public void getAccessToken(Login login, LoadAccessTokenCallback callback) {
        if(remoteDataSource!= null){
            remoteDataSource.getAccessToken(login, callback);
        }else {
            callback.onNoTokenAvailable(new DomainError(DomainError.ErrorType.REPOSITORY_ERROR));
        }
    }

    @Override
    public void getProfesores(String token, LoadProfesorCallback callback) {
        /*
        Si no estan en la base de datos, los obtenemos de web service.
         */
        remoteDataSource.getProfesores(token, callback);
/*
        if(roomDataSource != null){
            Log.d("TAG1", "Hola2");
            roomDataSource.getProfesores(token, callback);
        } else {
            Log.d("TAG1", "Hacer Peticion al Servidor");
            remoteDataSource.getProfesores(token, callback);
        }
        */
    }
}
