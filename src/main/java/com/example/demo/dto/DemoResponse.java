package com.example.demo.dto;

import java.util.List;

public class DemoResponse {

    String message;
    boolean hasErrors;
    List<String> errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public DemoResponse(String message, boolean hasErrors, List<String> errors) {
        this.message = message;
        this.hasErrors = hasErrors;
        this.errors = errors;
    }
}
