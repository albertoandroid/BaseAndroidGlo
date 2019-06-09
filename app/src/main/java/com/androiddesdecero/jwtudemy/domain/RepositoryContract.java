package com.androiddesdecero.jwtudemy.domain;

import android.content.Context;

import com.androiddesdecero.jwtudemy.domain.model.DomainError;
import com.androiddesdecero.jwtudemy.model.Login;
import com.androiddesdecero.jwtudemy.model.Profesor;

import java.util.List;

/**
 * Repository pattern interface to be implemented by Data sources from Data layer
 *
 * Return types should belong to Domain Layer
 */
public interface RepositoryContract {

    interface LoadAccessTokenCallback{
        void onTokenLoaded(String accessToken);
        void onNoTokenAvailable(DomainError domainError);
    }

    void saveAccessToken(Context context, String token);
    void getAccessToken(Login login, LoadAccessTokenCallback callback);


    interface LoadProfesorCallback{
        void onProfesorLoaded(List<Profesor> profesors);
        void onDataNotAvailable(DomainError domainError);
    }

    void getProfesores(String token, LoadProfesorCallback callback);


}
