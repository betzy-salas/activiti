package com.example.activiti.generic.model;

public class ExternalServicesResponse<T> {
    private T body;

    public ExternalServicesResponse(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}



