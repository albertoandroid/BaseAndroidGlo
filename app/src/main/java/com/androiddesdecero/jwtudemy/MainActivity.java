package com.androiddesdecero.jwtudemy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androiddesdecero.jwtudemy.api.WebServiceApi;
import com.androiddesdecero.jwtudemy.api.WebServiceJWT;
import com.androiddesdecero.jwtudemy.data.local.sharedpreferences.SharedPreferencesManager;
import com.androiddesdecero.jwtudemy.di.Injector;
import com.androiddesdecero.jwtudemy.domain.base.UseCase;
import com.androiddesdecero.jwtudemy.domain.base.UseCaseHandler;
import com.androiddesdecero.jwtudemy.domain.usecase.GetProfesoresUseCase;
import com.androiddesdecero.jwtudemy.domain.usecase.GetTokenUseCase;
import com.androiddesdecero.jwtudemy.domain.usecase.SaveAccessTokenUseCase;
import com.androiddesdecero.jwtudemy.model.Login;
import com.androiddesdecero.jwtudemy.model.Profesor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
    Eclipse proyecto backend JWT2
    java
    https://github.com/googlesamples/android-architecture-components/tree/b1a194c1ae267258cd002e2e1c102df7180be473
GithubBrowserSample

    //Views
    1.1- BaseViewModel
    1.2- BaseFragment
    1.3- Base Activity

    2.1- Home Activity
    2.2- Home Fragment
    2.2- HomeViewModel

    3.1- HomeViewModelFactory
    3.2- Injector
    3.3- On HomeFragment -> protected HomeViewModel getViewModel() {}

    //DATA
    4.1- Interfaz RepositoryContract
    4.2- DomainError
    4.3- Repository
    4.4- RemoteDataSource
    4.5- WebServiceApi

    4.6- ErrorDataParser
    4.7- ErrorTypeResponse
    4.8- ErrorResponse
    4.9- ProfesorDataParser
    4.10- ProfesorListResponse

    5.1- WebServiceJWT
    5.2- buildConfigField "String", "PFM_BASE_URL", '\"https://pfm-dev-mid-san-globile-dev.appls.boaw.paas.gsnetcloud.corp/\"'
    5.3- SharedPreferencesManager
    5.4- DemoDataSource
    5.5- LocalDataSource
    5.6- profesor.json

    6.1- UseCase
    6.2- GetProfesoresUseCase
    6.3- UseCaseHandler
    6.4- UseCaseScheduler
    6.5- UseCaseThreadPoolScheduler
    6.6- Añadir Java8

    7.1- Acabar Injector con DATA y Domain
    7.2- Repository
    7.3- GetProfesoresUseCase

    /------------------------------------

    CREAR UN NUEVO CASO DE USO

    -------------------------------------/
    1.1- RepositoryContract -> add interfaz
    1.2- Repository
    1.3- RemoteDataSource
    1.4- LocalDataSource
    1.5- DemoDataSource
    1.6- GetUseCase

    2.1- Injector -public static GetTokenUseCase

    3.1- Utilizar Caso de Uso en Activity

    4.1- DemoDataSource -> Hacer el Demo
    4.2- TokenResponse
    4.3- TokenDataParser
    4.4- Cambiar BaseActivityEnviorement


    /------------------------------------

    ROOM;

    -------------------------------------/
    1.1- Crear ProfesorEntity
    1.2- ProfesorDao
    1.3- ProfesorDatabase
    1.4- RoomDataSource
    1.5- AddRoomDataSource to Repository
     */


public class MainActivity extends BaseActivity {

    private EditText etUserName;
    private EditText etPassword;
    private Button btToken;
    private Button btObtenerRecurso;
    private Button btSolicitudTokenErroneo;
    private Button btSolicitudUseCase;
    private Button btSolicitudTokenUseCase;
    private String token;


    /*
    Nuevo
     */
    GetProfesoresUseCase getProfesoresUseCase;
    UseCaseHandler useCaseHandler;
    GetTokenUseCase getTokenUseCase;
    SaveAccessTokenUseCase saveAccessTokenUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentEnviroment();
        setUpView();
        useCaseHandler = Injector.provideUseCaseHandler();
        getProfesoresUseCase = Injector.provideGetProfesoresUseCase(Injector.provideRepository(currentEnviroment));
        getTokenUseCase = Injector.provideTokenUseCase(Injector.provideRepository(currentEnviroment));
        saveAccessTokenUseCase = Injector.provideSaveAccessTokenUseCase(Injector.provideRepository(currentEnviroment));
    }

    private void getToken(){
        Login login = new Login();
        login.setUser(etUserName.getText().toString());
        login.setPassword(etPassword.getText().toString());
        getTokenUseCase.setRequestValues(new GetTokenUseCase.RequestValues(login))
                .setUseCaseCallback(new UseCase.UseCaseCallback<GetTokenUseCase.ResponseValue, UseCase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetTokenUseCase.ResponseValue response) {
                        Log.d("TAG1", "hola Token");
                        token = response.getAccessTokent();
                        saveAccessTokenUseCase.setRequestValues(new SaveAccessTokenUseCase.RequestValues(getApplicationContext(), token));
                        useCaseHandler.execute(saveAccessTokenUseCase);

                        Log.d("TAG1", "El mega Token: " + SharedPreferencesManager.getInstance().readToken(getApplicationContext()));
                    }

                    @Override
                    public void onError(UseCase.ErrorValue errorResponse) {
                        Log.d("TAG1", "Error: " + errorResponse.getErrorData().getMessage());
                    }
                });
        useCaseHandler.execute(getTokenUseCase);
    }

    private void setUpUseCases(){

        getProfesoresUseCase.setRequestValues(new GetProfesoresUseCase.RequestValues("Bearer "+ token))
                .setUseCaseCallback(new UseCase.UseCaseCallback<GetProfesoresUseCase.ResponseValue, UseCase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetProfesoresUseCase.ResponseValue response) {
                        Log.d("TAG1", "Room: " + "que pasa" + response.getProfesors().size());

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

    private void setUpView(){
        token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvIiwidXNlcklkIjoiMiIsInJvbGUiOiJBZG1pbiJ9.A6_dKk_GcyzsYIiHzzo8Q7nqeFePjera56KUoFVbNK4";
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btToken = findViewById(R.id.btToken);
        btToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerToken();
            }
        });
        btObtenerRecurso = findViewById(R.id.btObtenerRecurso);
        btObtenerRecurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerRecursoConToken();
            }
        });

        btSolicitudTokenErroneo = findViewById(R.id.btSolicitudTokenErroneo);
        btSolicitudTokenErroneo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerRecursoConTokenErroneo();
            }
        });

        btSolicitudUseCase = findViewById(R.id.btSolicitudUseCase);
        btSolicitudUseCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpUseCases();
            }
        });

        btSolicitudTokenUseCase = findViewById(R.id.btSolicitudTokenUseCase);
        btSolicitudTokenUseCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToken();
            }
        });


    }


    private void obtenerRecursoConTokenErroneo(){
        token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvIiwidXNlcklkIjoiMiIsInJvbGUiOiJBZG1pbiJ9.A6_dKk_GcyzsYIiHzzo8Q7nqeFePjera56KUoFVbNK4";
        String authHeader = "Bearer " + token;
        Call<List<Profesor>> call = WebServiceJWT
                .getInstance()
                .createService(WebServiceApi.class)
                .getProfesores(authHeader);

        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Movimiento Bancario: " + i + " " + response.body().get(i));
                    }
                }else{
                    Log.d("TAG1", "Token es incorrecto y no podemos obtener la respuesta");
                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {

            }
        });
    }

    private void obtenerRecursoConToken(){
        String authHeader = "Bearer " + token;
        Call<List<Profesor>> call = WebServiceJWT
                .getInstance()
                .createService(WebServiceApi.class)
                .getProfesores(authHeader);

        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Movimiento Bancario: " + i + " " + response.body().get(i).getNombre());
                    }
                }else{
                    Log.d("TAG1", "Token es incorrecto y no podemos obtener la respuesta");
                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {

            }
        });
    }

    private void obtenerToken(){
        Login login = new Login();
        login.setUser(etUserName.getText().toString());
        login.setPassword(etPassword.getText().toString());
        Call<List<String>> call = WebServiceJWT
                .getInstance()
                .createService(WebServiceApi.class)
                .obtenerToken(login);

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code()==200){
                    Log.d("TAG1", "El token es: " + token);
                    token = response.body().get(0).toString();
                }else if(response.code()==401){
                    Log.d("TAG1", "No Autorizado - XXXX");
                }else{
                    Log.d("TAG1", "No obtenido token - xxx");
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("TAG1", "vvvNo obtenido token - xxx");

            }
        });
    }
}



















