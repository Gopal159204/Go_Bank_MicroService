package com.gobank.Service.Exception;

public class ResourceNotFoundException extends  RuntimeException {
    private  String resoruceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String resoruceName, String fieldName,String fieldValue){
        super(String.format("%s not found with this given data %s : &s", resoruceName,fieldName,fieldValue));
    }
}
