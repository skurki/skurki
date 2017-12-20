package com.hd.userprofile.exception;

public class UserMessage {

    private String errorMessage;

    public UserMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
