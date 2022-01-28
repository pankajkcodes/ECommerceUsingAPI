package com.pankajkcodes.phpapiecommerce;

public class SignupResponseModel {
    String message;

    public SignupResponseModel(String message) {
        this.message = message;
    }

    public SignupResponseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
