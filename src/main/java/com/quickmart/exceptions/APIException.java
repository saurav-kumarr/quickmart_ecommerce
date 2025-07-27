package com.quickmart.exceptions;

public class APIException extends RuntimeException {

    private static final long serialVaersionUID = 1L;

    public APIException(){
    }

    public APIException(String message){
        super(message);
    }

}
