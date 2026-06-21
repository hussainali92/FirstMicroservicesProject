package com.in28minutes.microservices.currency_exchange_services.Exception;

public class ErrorDetails {
    private String error;
    private String request;

    public ErrorDetails(String error, String request) {
        this.error = error;
        this.request = request;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
