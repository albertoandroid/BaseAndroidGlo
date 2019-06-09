package com.androiddesdecero.jwtudemy.domain.model.parser;

import com.androiddesdecero.jwtudemy.data.remote.responses.ProfesorListResponse;
import com.androiddesdecero.jwtudemy.model.Profesor;

import java.util.ArrayList;
import java.util.List;

public class ProfesorDataParser {
    private static ProfesorDataParser INSTANCE;

    public static ProfesorDataParser getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ProfesorDataParser();
        }
        return INSTANCE;
    }

    public Profesor transform(ProfesorListResponse.ProfesorResponse response){
        return new Profesor(response.getId(), response.getNombre(), response.getEmail(), response.getPassword(), response.getFoto());
    }

    public List<Profesor> transform(List<ProfesorListResponse.ProfesorResponse> response){

        ArrayList<Profesor> profesorDomain = new ArrayList<>();

        for(ProfesorListResponse.ProfesorResponse item: response){
            profesorDomain.add(transform(item));
        }
        return profesorDomain;
    }
}
