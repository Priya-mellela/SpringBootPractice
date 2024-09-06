package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class DemoServiceException  extends Exception{

    String message ;
    HttpStatus httpStatus;

    public DemoServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {

        return httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
