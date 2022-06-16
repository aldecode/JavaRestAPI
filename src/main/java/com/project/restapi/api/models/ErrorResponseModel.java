package com.project.restapi.api.models;

public class ErrorResponseModel {
    private final String timestamp;

    private final Integer status;

    private final String error;

    private final String message;


    public ErrorResponseModel(String timestamp, Integer status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
