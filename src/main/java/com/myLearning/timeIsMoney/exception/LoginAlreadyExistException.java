package com.myLearning.timeIsMoney.exception;

public class LoginAlreadyExistException extends RuntimeException {

    public LoginAlreadyExistException(String explanation) {
        super(explanation);
    }
}
