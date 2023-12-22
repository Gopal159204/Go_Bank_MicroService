package com.gobank.Service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyPresent extends  RuntimeException{
    public LoanAlreadyPresent(String message) {
        super(message);
    }
}
