package com.project.restapi.api.models.user;

public class UserLoginResponseModel {
    private String token;

    public UserLoginResponseModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
