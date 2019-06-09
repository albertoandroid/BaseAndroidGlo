package com.androiddesdecero.jwtudemy.data.local;

import android.content.Context;
import android.util.Log;

import com.androiddesdecero.jwtudemy.api.WebServiceApi;
import com.androiddesdecero.jwtudemy.data.remote.RemoteDataSource;
import com.androiddesdecero.jwtudemy.db.ProfesorDao;
import com.androiddesdecero.jwtudemy.db.ProfesorDatabase;
import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.domain.model.DomainError;
import com.androiddesdecero.jwtudemy.domain.model.parser.ErrorDataParser;
import com.androiddesdecero.jwtudemy.model.Login;
import com.androiddesdecero.jwtudemy.model.Profesor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomDataSource implements RepositoryContract {

    private static RoomDataSource INSTANCE;

    private static ProfesorDatabase profesorDb;

    public static RoomDataSource getInstance(Context context){
        if(INSTANCE == null){
            profesorDb = ProfesorDatabase.getInstance(context);
            INSTANCE = new RoomDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void saveAccessToken(Context context, String token) {
        //No Hacer Nada
    }

    @Override
    public void getAccessToken(Login login, LoadAccessTokenCallback callback) {
        //No hacer nada
    }

    @Override
    public void getProfesores(String token, LoadProfesorCallback callback) {
        if(profesorDb.profesorDao().getAll() != null){
            callback.onProfesorLoaded(profesorDb.profesorDao().getAll());
        } else {
            callback.onDataNotAvailable(new DomainError(DomainError.ErrorType.PARSING_WRONG_DATA));
        }
    }

    public void deleteProfesores(){
        profesorDb.profesorDao().deleteTodo();
    }
}
