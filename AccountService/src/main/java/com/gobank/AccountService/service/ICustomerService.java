package com.gobank.AccountService.service;

import com.gobank.AccountService.Dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchcustomerDetials(String mobileNumber,String correlationId);
}
