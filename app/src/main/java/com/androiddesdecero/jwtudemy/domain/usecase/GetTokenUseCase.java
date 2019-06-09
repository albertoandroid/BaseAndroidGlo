package com.androiddesdecero.jwtudemy.domain.usecase;

import android.content.Context;

import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.domain.base.UseCase;
import com.androiddesdecero.jwtudemy.domain.model.DomainError;
import com.androiddesdecero.jwtudemy.model.Login;
import com.androiddesdecero.jwtudemy.model.Profesor;

import java.util.List;

public class GetTokenUseCase extends UseCase<GetTokenUseCase.RequestValues, GetTokenUseCase.ResponseValue, UseCase.ErrorValue> {

    private RepositoryContract repository;

    public GetTokenUseCase(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getAccessToken(requestValues.login, new RepositoryContract.LoadAccessTokenCallback() {
            @Override
            public void onTokenLoaded(String accessToken) {
                getUseCaseCallback().onSuccess(new ResponseValue(accessToken));
            }

            @Override
            public void onNoTokenAvailable(DomainError domainError) {
                getUseCaseCallback().onError(new ErrorValue(domainError));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private Login login;

        public RequestValues(Login login){
            this.login = login;
        }

        public Login getLogin(){
            return login;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private String accessTokent;

        public ResponseValue(String accessTokent) {
            this.accessTokent = accessTokent;
        }

        public String getAccessTokent() {
            return accessTokent;
        }
    }
}
