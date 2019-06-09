package com.androiddesdecero.jwtudemy.data.remote;

import android.content.Context;
import android.util.Log;

import com.androiddesdecero.jwtudemy.api.WebServiceApi;
import com.androiddesdecero.jwtudemy.data.remote.responses.ErrorResponse;
import com.androiddesdecero.jwtudemy.data.remote.responses.ErrorTypeResponse;
import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.domain.model.parser.ErrorDataParser;
import com.androiddesdecero.jwtudemy.model.Login;
import com.androiddesdecero.jwtudemy.model.Profesor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements RepositoryContract {

    private static RemoteDataSource INSTANCE;

    private final WebServiceApi webServiceApi;

    public static RemoteDataSource getInstance(WebServiceApi webServiceApi){
        if(INSTANCE == null){
            INSTANCE = new RemoteDataSource(webServiceApi);
            Log.d("TAG1", "Hola4");
        }
        return INSTANCE;
    }

    private RemoteDataSource(WebServiceApi webServiceApi){
        this.webServiceApi = webServiceApi;
    }

    @Override
    public void saveAccessToken(Context context, String token) {
        //Via Shared Preferences
    }

    @Override
    public void getAccessToken(Login login, LoadAccessTokenCallback callback) {
        webServiceApi.obtenerToken(login).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                try {
                    if(response.isSuccessful()){
                        callback.onTokenLoaded(response.body().get(0));
                    } else {
                        callback.onNoTokenAvailable(
                                ErrorDataParser.getInstance().transform(
                                        generateErrorResponse(response.code(), response.errorBody().string())
                                )
                        );
                    }
                } catch (Exception e){
                    callback.onNoTokenAvailable(
                            ErrorDataParser.getInstance().transform(
                                    generateErrorResponse(-1, e.getMessage())
                            )
                    );
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                callback.onNoTokenAvailable(
                        ErrorDataParser.getInstance().transform(
                                generateErrorResponse(-1, t.getCause().getMessage())));
            }
        });
    }

    @Override
    public void getProfesores(String token, final LoadProfesorCallback callback) {
        Log.d("TAG1", "Hola3");

        webServiceApi.getProfesores(token).enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                try {
                    if(response.isSuccessful()){
                        callback.onProfesorLoaded(response.body());
                    } else {
                        callback.onDataNotAvailable(
                                ErrorDataParser.getInstance().transform(
                                        generateErrorResponse(response.code(), response.errorBody().string())
                                )
                        );
                    }
                } catch (Exception e){
                    callback.onDataNotAvailable(
                            ErrorDataParser.getInstance().transform(
                                    generateErrorResponse(-1, e.getMessage())
                            )
                    );
                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {
                callback.onDataNotAvailable(
                        ErrorDataParser.getInstance().transform(
                                generateErrorResponse(-1, t.getCause().getMessage())));
            }
        });
    }




    private ErrorResponse generateErrorResponse(int code, String message){

        ErrorTypeResponse errorType;

        switch (code){

            case -1: errorType = ErrorTypeResponse.EXCEPTION_OCURRED; break;

            case 401: errorType = ErrorTypeResponse.AUTHORIZATION_ERROR; break;
            case 403: errorType = ErrorTypeResponse.FORBIDDEN; break;
            case 404: errorType = ErrorTypeResponse.NOT_FOUND; break;
            case 500: errorType = ErrorTypeResponse.CANNOT_REACH_ADDRESS; break;

            default: errorType = ErrorTypeResponse.NET_GENERIC_ERROR; break;

        }

        return new ErrorResponse(code, errorType, message);

    }
}
