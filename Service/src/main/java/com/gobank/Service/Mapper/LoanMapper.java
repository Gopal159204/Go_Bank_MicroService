package com.gobank.Service.Mapper;

import com.gobank.Service.Dto.LoanDto;
import com.gobank.Service.Entity.LoanEntity;

public class LoanMapper {
    public static LoanDto mapToLoandDto(LoanEntity loanEntity, LoanDto loanDto){
        loanDto.setMobileNumber(loanEntity.getMobileNumber());
        loanDto.setLoanNumber(loanEntity.getLoanNumber());
        loanDto.setLoanType(loanEntity.getLoanType());
        loanDto.setTotalLoan(loanEntity.getTotalLoan());
        loanDto.setAmountPaid(loanEntity.getAmountPaid());
        loanDto.setOutstandingAmount(loanEntity.getOutstandingAmount());
        return  loanDto;
    }
    public static LoanEntity mapToLoanEn(LoanEntity loanEntity,LoanDto loanDto){
        loanEntity.setMobileNumber(loanDto.getMobileNumber());
        loanEntity.setLoanNumber(loanDto.getLoanNumber());
        loanEntity.setLoanType(loanDto.getLoanType());
        loanEntity.setTotalLoan(loanDto.getTotalLoan());
        loanEntity.setAmountPaid(loanDto.getAmountPaid());
        loanEntity.setOutstandingAmount(loanDto.getOutstandingAmount());
        return  loanEntity;
    }
}
