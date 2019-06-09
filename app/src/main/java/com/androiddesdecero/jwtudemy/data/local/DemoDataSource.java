package com.androiddesdecero.jwtudemy.data.local;

import android.content.Context;
import android.util.Log;

import com.androiddesdecero.jwtudemy.data.remote.responses.ProfesorListResponse;
import com.androiddesdecero.jwtudemy.data.remote.responses.TokenResponse;
import com.androiddesdecero.jwtudemy.domain.RepositoryContract;
import com.androiddesdecero.jwtudemy.domain.model.DomainError;
import com.androiddesdecero.jwtudemy.domain.model.parser.ProfesorDataParser;
import com.androiddesdecero.jwtudemy.domain.model.parser.TokenDataParser;
import com.androiddesdecero.jwtudemy.model.Login;
import com.androiddesdecero.jwtudemy.model.Profesor;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class DemoDataSource implements RepositoryContract {

    private static DemoDataSource INSTANCE;

    public static DemoDataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DemoDataSource();
        }
        return INSTANCE;
    }

    private DemoDataSource(){}

    @Override
    public void saveAccessToken(Context context, String token) {

    }

    @Override
    public void getAccessToken(Login login, LoadAccessTokenCallback callback) {
        try{
            Thread.sleep(2000);
            String json = readAssetJson("assets/token.json");
            Log.d("TAG1", "XX1" + json);
            Gson gson = new Gson();
            TokenResponse response = gson.fromJson(json, TokenResponse.class);
            //List<Profesor> response = gson.fromJson(json, );
            Log.d("TAG1", "XX2");
            callback.onTokenLoaded(TokenDataParser.getInstance().transform(response));
            Log.d("TAG1", "XX3");


        }catch (Exception e){
            Log.d("TAG1", "XXerror");
            callback.onNoTokenAvailable(new DomainError(DomainError.ErrorType.PARSING_WRONG_DATA));
        }
    }

    @Override
    public void getProfesores(String token, LoadProfesorCallback callback) {
        try{
            Thread.sleep(2000);
            String json = readAssetJson("assets/profesores.json");
            Log.d("TAG1", "XX1" + json);
            Gson gson = new Gson();
            ProfesorListResponse response = gson.fromJson(json, ProfesorListResponse.class);
            //List<Profesor> response = gson.fromJson(json, );
            Log.d("TAG1", "XX2");
            callback.onProfesorLoaded(ProfesorDataParser.getInstance().transform(response.getProfesorResponses()));
            Log.d("TAG1", "XX3");


        }catch (Exception e){
            Log.d("TAG1", "XXerror");
            callback.onDataNotAvailable(new DomainError(DomainError.ErrorType.PARSING_WRONG_DATA));
        }
    }

    private String readAssetJson(String file){

        StringBuilder json = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(Objects.requireNonNull(getClass().getClassLoader())).getResourceAsStream(file)))) {

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                json.append(mLine);
            }

        } catch (IOException e) {
            //log the exception
        }

        return json.toString();

    }
}
