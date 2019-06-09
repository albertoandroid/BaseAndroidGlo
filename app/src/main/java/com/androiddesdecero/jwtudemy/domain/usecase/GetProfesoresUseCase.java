package com.androiddesdecero.jwtudemy.domain.usecase;

import android.util.Log;

import com.androiddesdecero.jwtudemy.data.Repository;
import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.domain.base.UseCase;
import com.androiddesdecero.jwtudemy.domain.model.DomainError;
import com.androiddesdecero.jwtudemy.model.Profesor;

import java.util.List;

public class GetProfesoresUseCase extends UseCase<GetProfesoresUseCase.RequestValues, GetProfesoresUseCase.ResponseValue, UseCase.ErrorValue> {

    private RepositoryContract repository;

    public GetProfesoresUseCase(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getProfesores(requestValues.getAccessToken(), new RepositoryContract.LoadProfesorCallback() {
            @Override
            public void onProfesorLoaded(List<Profesor> profesors) {
                Log.d("TAG1", "GetProfesoresUseCase");
                getUseCaseCallback().onSuccess(new ResponseValue(profesors));
            }
            @Override
            public void onDataNotAvailable(DomainError domainError) {
                Log.d("TAG1", "Error: GetProfesoresUseCase");
                getUseCaseCallback().onError(new ErrorValue(domainError));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private String accessToken;

        public RequestValues(String accessToken){
            this.accessToken = accessToken;
        }

        public String getAccessToken(){
            return accessToken;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private List<Profesor> profesors;

        public ResponseValue(List<Profesor> profesors){
            this.profesors = profesors;
        }

        public List<Profesor> getProfesors(){
            return profesors;
        }

    }
}
