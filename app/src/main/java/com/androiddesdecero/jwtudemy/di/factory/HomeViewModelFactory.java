package com.androiddesdecero.jwtudemy.di.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.androiddesdecero.jwtudemy.data.Repository;
import com.androiddesdecero.jwtudemy.di.Injector;
import com.androiddesdecero.jwtudemy.ui.HomeViewModel;

public class HomeViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final Context context;
    private final Repository repository;

    public HomeViewModelFactory(Context context, Repository repository){
        this.context = context;
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(context,
                Injector.provideUseCaseHandler(),
                Injector.provideSaveAccessTokenUseCase(repository),
                Injector.provideTokenUseCase(repository),
                Injector.provideGetProfesoresUseCase(repository));
    }
}
