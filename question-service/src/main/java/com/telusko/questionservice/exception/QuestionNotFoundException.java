package com.telusko.questionservice.exception;

public class QuestionNotFoundException extends RuntimeException{

    public QuestionNotFoundException(String message){
        super(message);
    }
}
