package com.arun.practice.onboardingdocumentscollectionservice.exception;

public enum DocumentCollectionServiceException {

    DOCUMENT_SAVE_EXCEPTION("Document not save",1081),
    JSON_CONVERSION_EXCEPTION("Json conversion exception ",1082),
    DRIVER_NOT_PRESENT_EXCEPTION("Driver not present",1083);
    private final String message;
    private final Integer errorCode;

    DocumentCollectionServiceException(String message,Integer errorCode){
        this.message = message;
        this.errorCode = errorCode;
    }
    public static DocumentCollectionServiceException getExceptionByErrorCode(int errorCode){
        for( DocumentCollectionServiceException exception:DocumentCollectionServiceException.values()){
            if(exception.errorCode == errorCode){
                return exception;
            }
        }
        return null;
    }
    public String getMessage(){
        return this.message;
    }
    public Integer getErrorCode(){
        return this.errorCode;
    }
}
