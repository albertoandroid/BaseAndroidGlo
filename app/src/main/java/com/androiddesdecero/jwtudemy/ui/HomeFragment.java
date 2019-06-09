package com.androiddesdecero.jwtudemy.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.androiddesdecero.jwtudemy.BaseFragment;
import com.androiddesdecero.jwtudemy.BaseViewModel;
import com.androiddesdecero.jwtudemy.R;
import com.androiddesdecero.jwtudemy.di.Injector;
import com.androiddesdecero.jwtudemy.di.factory.HomeViewModelFactory;
import com.androiddesdecero.jwtudemy.model.Login;


public class HomeFragment extends BaseFragment {

    private EditText etUserName;
    private EditText etPassword;
    private Button btToken;
    private Button btObtenerRecurso;
    private Button btSolicitudTokenErroneo;
    private Button btSolicitudUseCase;
    private Button btSolicitudTokenUseCase;
    private String token;

    private HomeViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected BaseViewModel getViewModel() {
        if(viewModel == null){
            HomeViewModelFactory factory = Injector.provideHomeViewModelFactory(getContext(), repository);
            viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        }
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);
        setUpView(view);
        return view;
    }

    private void setUpView(View view){
        etUserName = view.findViewById(R.id.etUserName);
        etPassword = view.findViewById(R.id.etPassword);
        btToken = view.findViewById(R.id.btToken);
        btToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG1", "·232");
                obtenerToken();
            }
        });
        btObtenerRecurso = view.findViewById(R.id.btObtenerRecurso);
        btObtenerRecurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               obtenerRecursoConToken();
            }
        });

        btSolicitudTokenErroneo = view.findViewById(R.id.btSolicitudTokenErroneo);
        btSolicitudTokenErroneo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  obtenerRecursoConTokenErroneo();
            }
        });

        btSolicitudUseCase = view.findViewById(R.id.btSolicitudUseCase);
        btSolicitudUseCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  setUpUseCases();
            }
        });

        btSolicitudTokenUseCase = view.findViewById(R.id.btSolicitudTokenUseCase);
        btSolicitudTokenUseCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  getToken();
            }
        });


    }

    private void obtenerRecursoConToken(){
        viewModel.obtenerRecursoConToken();
    }

    private void obtenerToken(){
        Login login = new Login();
        login.setUser(etUserName.getText().toString());
        login.setPassword(etPassword.getText().toString());
        viewModel.obtenerToken(login);
    }
}
