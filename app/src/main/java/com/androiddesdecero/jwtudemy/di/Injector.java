package com.androiddesdecero.jwtudemy.di;

import android.content.Context;
import android.util.Log;

import com.androiddesdecero.jwtudemy.Environments;
import com.androiddesdecero.jwtudemy.api.WebServiceApi;
import com.androiddesdecero.jwtudemy.api.WebServiceJWT;
import com.androiddesdecero.jwtudemy.data.Repository;
import com.androiddesdecero.jwtudemy.data.local.DemoDataSource;
import com.androiddesdecero.jwtudemy.data.local.LocalDataSource;
import com.androiddesdecero.jwtudemy.data.local.RoomDataSource;
import com.androiddesdecero.jwtudemy.data.remote.RemoteDataSource;
import com.androiddesdecero.jwtudemy.di.factory.HomeViewModelFactory;
import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.domain.base.UseCaseHandler;
import com.androiddesdecero.jwtudemy.domain.usecase.GetProfesoresUseCase;
import com.androiddesdecero.jwtudemy.domain.usecase.GetTokenUseCase;
import com.androiddesdecero.jwtudemy.domain.usecase.SaveAccessTokenUseCase;
import com.androiddesdecero.jwtudemy.ui.HomeViewModel;
import com.androiddesdecero.jwtudemy.utils.AndroidContextHelper;

public class Injector {
    //ViewModel

    public static HomeViewModelFactory provideHomeViewModelFactory(Context context, Repository repository){
        return new HomeViewModelFactory(context, repository);
    }


    // Domain
    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static GetTokenUseCase provideTokenUseCase(RepositoryContract repository){
        return new GetTokenUseCase(repository);
    }

    public static SaveAccessTokenUseCase provideSaveAccessTokenUseCase(RepositoryContract repository){
        return new SaveAccessTokenUseCase(repository);
    }

    public static GetProfesoresUseCase provideGetProfesoresUseCase(RepositoryContract repository){
        return new GetProfesoresUseCase(repository);
    }

    //DATA
    public static Repository provideRepository(Environments environment){
        switch(environment) {

            case DEMO_LOCAL:
                Log.d("TAG1" ,"Hola Demo_lOCAL");
                return Repository.newInstance(
                        DemoDataSource.getInstance(),
                        DemoDataSource.getInstance(),
                        DemoDataSource.getInstance());

            default:
            case DEV:
                Log.d("TAG1" ,"Hola DEV");
                return Repository.newInstance(
                        LocalDataSource.getInstance(),
                        RemoteDataSource.getInstance(provideWebServiceApi()),
                        RoomDataSource.getInstance(AndroidContextHelper.currentContext));
        }

    }

    public static WebServiceApi provideWebServiceApi(){
        return WebServiceJWT.createService(WebServiceApi.class);
    }
}
