package com.androiddesdecero.jwtudemy.domain.model.parser;

import com.androiddesdecero.jwtudemy.data.remote.responses.ErrorResponse;
import com.androiddesdecero.jwtudemy.data.remote.responses.ErrorTypeResponse;
import com.androiddesdecero.jwtudemy.domain.model.DomainError;

public class ErrorDataParser {
    private static ErrorDataParser INSTANCE;

    public static ErrorDataParser getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new ErrorDataParser();
        }
        return INSTANCE;

    }

    public DomainError transform(ErrorResponse errorResponse){

        String message = "(" + errorResponse.getStatusCode() + ")" + errorResponse.getErrorMessage();

        return new DomainError(transform(errorResponse.getErrorType()), message);

    }

    private DomainError.ErrorType transform(ErrorTypeResponse errorTypeResponse){

        if (errorTypeResponse == null){
            return DomainError.ErrorType.NET_GENERIC_ERROR;
        }

        switch (errorTypeResponse){

            case AUTHORIZATION_ERROR: return DomainError.ErrorType.NET_AUTHORIZATION_ERROR;
            case CANNOT_REACH_ADDRESS: return DomainError.ErrorType.NET_CANNOT_REACH_ADDRESS;
            case FORBIDDEN: return DomainError.ErrorType.NET_FORBIDDEN;
            case NOT_FOUND: return DomainError.ErrorType.NET_NOT_FOUND;
            case EXCEPTION_OCURRED: return DomainError.ErrorType.APPLICATION_EXCEPTION;

            default:
            case NET_GENERIC_ERROR: return DomainError.ErrorType.NET_GENERIC_ERROR;

        }

    }
}
