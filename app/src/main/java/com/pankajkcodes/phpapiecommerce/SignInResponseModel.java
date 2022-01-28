package com.pankajkcodes.phpapiecommerce;

public class SignInResponseModel {
    String message;

    public SignInResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
