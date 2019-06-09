package com.androiddesdecero.jwtudemy.domain.usecase;

import android.content.Context;

import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.domain.base.UseCase;
import com.androiddesdecero.jwtudemy.domain.model.DomainError;
import com.androiddesdecero.jwtudemy.model.Login;

public class SaveAccessTokenUseCase extends UseCase<SaveAccessTokenUseCase.RequestValues,
        SaveAccessTokenUseCase.ResponseValue, UseCase.ErrorValue> {

    private RepositoryContract repository;

    public SaveAccessTokenUseCase(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(SaveAccessTokenUseCase.RequestValues requestValues) {
        repository.saveAccessToken(requestValues.context, requestValues.accessToken);
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private Context context;
        private String accessToken;

        public RequestValues(Context context, String accessToken) {
            this.context = context;
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}

