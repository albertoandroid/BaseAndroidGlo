package com.androiddesdecero.jwtudemy;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androiddesdecero.jwtudemy.data.Repository;
import com.androiddesdecero.jwtudemy.di.Injector;

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {

    private BaseActivity baseActivity;
    protected T viewModel;
    protected abstract T getViewModel();
    protected Repository repository;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
            baseActivity = (BaseActivity)context;
        }else{
            throw new RuntimeException(context.toString() +
                    " cannot be instanced out of the component scope");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = Injector.provideRepository(getEnviroments());
        if(viewModel == null){
            viewModel = getViewModel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel = null;
        baseActivity = null;
    }

    protected Environments getEnviroments(){
        return baseActivity.getCurrentEnviroment();
    }
}
