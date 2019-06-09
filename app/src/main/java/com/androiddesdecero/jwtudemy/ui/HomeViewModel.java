package com.androiddesdecero.jwtudemy.ui;

import android.content.Context;
import android.util.Log;

import com.androiddesdecero.jwtudemy.BaseViewModel;
import com.androiddesdecero.jwtudemy.data.local.sharedpreferences.SharedPreferencesManager;
import com.androiddesdecero.jwtudemy.domain.base.UseCase;
import com.androiddesdecero.jwtudemy.domain.base.UseCaseHandler;
import com.androiddesdecero.jwtudemy.domain.usecase.GetProfesoresUseCase;
import com.androiddesdecero.jwtudemy.domain.usecase.GetTokenUseCase;
import com.androiddesdecero.jwtudemy.domain.usecase.SaveAccessTokenUseCase;
import com.androiddesdecero.jwtudemy.model.Login;

public class HomeViewModel extends BaseViewModel {

    private String token;
    private GetProfesoresUseCase getProfesoresUseCase;

    public HomeViewModel(Context context, UseCaseHandler useCaseHandler,
                         SaveAccessTokenUseCase saveAccessTokenUseCase, GetTokenUseCase getTokenUseCase,
                         GetProfesoresUseCase getProfesoresUseCase){
        super(context, useCaseHandler, saveAccessTokenUseCase, getTokenUseCase);

        this.getProfesoresUseCase = getProfesoresUseCase;

    }

    @Override
    public void init() {

    }

    public void obtenerToken(Login login){
        getTokenUseCase.setRequestValues(new GetTokenUseCase.RequestValues(login))
                .setUseCaseCallback(new UseCase.UseCaseCallback<GetTokenUseCase.ResponseValue, UseCase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetTokenUseCase.ResponseValue response) {
                        token = response.getAccessTokent();
                        Log.d("TAG1", "hola Token" + token);

                        saveAccessTokenUseCase.setRequestValues(new SaveAccessTokenUseCase.RequestValues(context, token));
                        useCaseHandler.execute(saveAccessTokenUseCase);

                        Log.d("TAG1", "El mega Token: " + SharedPreferencesManager.getInstance().readToken(context));
                    }

                    @Override
                    public void onError(UseCase.ErrorValue errorResponse) {
                        Log.d("TAG1", "Error: " + errorResponse.getErrorData().getMessage());
                    }
                });
        useCaseHandler.execute(getTokenUseCase);
    }

    public void obtenerRecursoConToken(){
        getProfesoresUseCase.setRequestValues(new GetProfesoresUseCase.RequestValues("Bearer "+ token))
                .setUseCaseCallback(new UseCase.UseCaseCallback<GetProfesoresUseCase.ResponseValue, UseCase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetProfesoresUseCase.ResponseValue response) {
                        Log.d("TAG1", "Room: " + "que pasa");

                        for(int i=0; i<response.getProfesors().size(); i++){
                            Log.d("TAG1", "Movimiento Bancario: " + i + " " + response.getProfesors().get(i));
                        }
                    }
                    @Override
                    public void onError(UseCase.ErrorValue errorResponse) {
                        Log.d("TAG1", "Error: " + errorResponse.getErrorData().getMessage());

                    }
                });
        useCaseHandler.execute(getProfesoresUseCase);
    }

}
