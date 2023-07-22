package com.arun.practice.onboardingdocumentscollectionservice.exception;

import lombok.Data;

@Data
public class RequestValidationException extends ServiceException{

    private String message;
    private Integer errorCode;
    public  RequestValidationException(String message,Integer code){
        super(message,code,new Exception(message + "error code:"+code));
        this.message = message;
        this.errorCode = code;
    }

}
