package com.gobank.AccountService.service.Impl;

import com.gobank.AccountService.Dto.AccountDto;
import com.gobank.AccountService.Dto.CardDto;
import com.gobank.AccountService.Dto.CustomerDetailsDto;
import com.gobank.AccountService.Dto.LoanDto;
import com.gobank.AccountService.Exception.ResourceNotFoundException;
import com.gobank.AccountService.entity.Account;
import com.gobank.AccountService.entity.Customer;
import com.gobank.AccountService.mapper.AccountMapper;
import com.gobank.AccountService.mapper.CustomerMapper;
import com.gobank.AccountService.repository.AccountsRepository;
import com.gobank.AccountService.repository.CustomerRepository;
import com.gobank.AccountService.service.ICustomerService;
import com.gobank.AccountService.service.client.CardsFeignClient;
import com.gobank.AccountService.service.client.LoanFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
     private CardsFeignClient cardsFeignClient;
    @Autowired
     private LoanFeignClient loanFeignClient;

    @Override
    public CustomerDetailsDto fetchcustomerDetials(String mobileNumber,String correlationId) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException(" customer ","mobileNumber",mobileNumber));
        Account account=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()-> {
            return new ResourceNotFoundException("Account", "customerID", customer.getCustomerId().toString());
        });
        CustomerDetailsDto customerDetailsDto= CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountDto(account,new AccountDto()));

        ResponseEntity<LoanDto>loansDtoResponseEntity=loanFeignClient.fetchLoan(correlationId,mobileNumber);
        customerDetailsDto.setLoanDto(loansDtoResponseEntity.getBody());
        ResponseEntity<CardDto>cardDtoResponseEntity=cardsFeignClient.getCardWithMobileNumber(correlationId,mobileNumber);
        customerDetailsDto.setCardDto(cardDtoResponseEntity.getBody());
        return  customerDetailsDto;




    }
}
