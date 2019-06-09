package com.androiddesdecero.jwtudemy.data.remote.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TokenResponse {

    @SerializedName("data")
    List<String> tokens;

    public TokenResponse(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<String> getTokens() {
        return tokens;
    }
}
