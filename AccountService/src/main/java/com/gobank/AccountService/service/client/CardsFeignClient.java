package com.gobank.AccountService.service.client;

import com.gobank.AccountService.Dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("card")
public interface CardsFeignClient {
    @GetMapping(value = "/card/get/{mobileNumber}",consumes = "application/json")
    public  ResponseEntity<CardDto>getCardWithMobileNumber(@RequestHeader("gobank-correlation-id")String correlationId, @PathVariable String mobileNumber);


}
