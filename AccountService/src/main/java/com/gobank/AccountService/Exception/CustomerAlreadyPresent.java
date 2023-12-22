package com.gobank.AccountService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyPresent extends   RuntimeException {
    public  CustomerAlreadyPresent(String message){
        super(message);
    }
}
