package com.gobank.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class CardAlreadyPresnt extends  RuntimeException {

    public CardAlreadyPresnt(String message) {
        super(message);
    }
}
