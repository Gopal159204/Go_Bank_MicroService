package com.gobank.AccountService.mapper;

import com.gobank.AccountService.Dto.AccountDto;
import com.gobank.AccountService.entity.Account;

public class AccountMapper {
    public  static AccountDto mapToAccountDto(Account account, AccountDto accountDto){
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return  accountDto;
    }
    public  static  Account mapToAccounts(AccountDto accountDto,Account account){
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return  account;
    }
}
