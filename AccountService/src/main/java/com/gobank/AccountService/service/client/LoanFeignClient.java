package com.gobank.AccountService.service.client;

import com.gobank.AccountService.Dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loan")
public interface LoanFeignClient {
    @GetMapping(value = "/loan/fetch",consumes = "application/json")
    public ResponseEntity<LoanDto>fetchLoan(@RequestHeader("gobank-correlation-id")String correlationId, @RequestParam String mobileNumber);






}
