package com.arun.practice.onboardingdocumentscollectionservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceException extends RuntimeException{
    private String message;
    private Integer errorCode;
    private Throwable exception;
    public  ServiceException(DocumentCollectionServiceException documentCollectionServiceException, Throwable exception){
        this.message = documentCollectionServiceException.getMessage();
        this.errorCode = documentCollectionServiceException.getErrorCode();
        this.exception = exception;
    }
}
