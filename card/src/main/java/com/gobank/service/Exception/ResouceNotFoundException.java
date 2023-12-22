package com.gobank.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResouceNotFoundException extends  RuntimeException {
    private String resourceName;
    private  String firldName;
    private String fieldValue;

    public ResouceNotFoundException(String resourceName, String firldName, String fieldValue) {

       super(String.format("%s card is not found with given card number or mobile number %s : %s",resourceName,firldName,fieldValue));

    }
}
