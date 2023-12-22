package com.gobank.AccountService.service;

import com.gobank.AccountService.Dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);


    boolean upadtedAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);

}
