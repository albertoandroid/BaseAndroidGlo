package com.androiddesdecero.jwtudemy.domain.model;

public class DomainError {

    public enum ErrorType {

        NET_AUTHORIZATION_ERROR,
        NET_FORBIDDEN,
        NET_NOT_FOUND,
        NET_CANNOT_REACH_ADDRESS,
        NET_GENERIC_ERROR,
        PARSING_WRONG_DATA,
        REPOSITORY_ERROR,
        APPLICATION_EXCEPTION

    }

    private ErrorType mErrorType;

    private String mMessage;

    public DomainError(ErrorType errorType){

        mErrorType = errorType;

        if (mErrorType == ErrorType.PARSING_WRONG_DATA) {
            mMessage = "Data requested doesn't match data received";
        } else {
            mMessage = "";
        }

    }

    public DomainError(ErrorType errorType, String message) {

        this.mErrorType = errorType;
        this.mMessage = message;

    }

    public ErrorType getErrorType() {
        return mErrorType;
    }

    public String getMessage() {
        return mMessage;
    }
}
