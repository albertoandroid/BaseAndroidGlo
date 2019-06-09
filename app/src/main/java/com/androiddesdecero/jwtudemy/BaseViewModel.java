package com.androiddesdecero.jwtudemy;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.androiddesdecero.jwtudemy.domain.base.UseCaseHandler;
import com.androiddesdecero.jwtudemy.domain.usecase.GetTokenUseCase;
import com.androiddesdecero.jwtudemy.domain.usecase.SaveAccessTokenUseCase;

public abstract class BaseViewModel extends ViewModel {

    protected Context context;
    protected UseCaseHandler useCaseHandler;
    protected SaveAccessTokenUseCase saveAccessTokenUseCase;
    protected GetTokenUseCase getTokenUseCase;
    protected String accessToken;

    public BaseViewModel(){}

    public abstract void init();

    public BaseViewModel(Context context, UseCaseHandler useCaseHandler,
                         SaveAccessTokenUseCase saveAccessTokenUseCase, GetTokenUseCase getTokenUseCase){
        this.context = context;
        this.useCaseHandler = useCaseHandler;
        this.saveAccessTokenUseCase = saveAccessTokenUseCase;
        this.getTokenUseCase = getTokenUseCase;
    }

    public SaveAccessTokenUseCase getSaveAccessTokenUseCase(){
        return saveAccessTokenUseCase;
    }

}
