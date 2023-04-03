package com.api.employeesdata.entities;

public class APIControllerErrorResponse {
    private String message;
    private long timestamp;

    public APIControllerErrorResponse() {
    }

    public APIControllerErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
