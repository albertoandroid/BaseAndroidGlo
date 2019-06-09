package com.androiddesdecero.jwtudemy.data.remote.responses;

public class ErrorResponse {
    private int mStatusCode;

    private ErrorTypeResponse mErrorType;

    private String mErrorMessage;

    public ErrorResponse(int statusCode, ErrorTypeResponse errorType, String errorMessage) {

        this.mStatusCode = statusCode;
        this.mErrorType = errorType;
        this.mErrorMessage = errorMessage;

    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public ErrorTypeResponse getErrorType() {
        return mErrorType;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
