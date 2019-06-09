package com.androiddesdecero.jwtudemy.domain.model.parser;

import com.androiddesdecero.jwtudemy.data.remote.responses.ProfesorListResponse;
import com.androiddesdecero.jwtudemy.data.remote.responses.TokenResponse;
import com.androiddesdecero.jwtudemy.model.Profesor;

import java.util.ArrayList;
import java.util.List;

public class TokenDataParser {

    private static TokenDataParser INSTANCE;

    public static TokenDataParser getInstance(){
        if(INSTANCE == null){
            INSTANCE = new TokenDataParser();
        }
        return INSTANCE;
    }

    public String transform(TokenResponse response){
        return response.getTokens().get(0);
    }
}

