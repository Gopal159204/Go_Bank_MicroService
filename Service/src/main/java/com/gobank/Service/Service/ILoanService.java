package com.gobank.Service.Service;

import com.gobank.Service.Dto.LoanDto;

public interface ILoanService {

      void createLoan(String mobileNumber);

    LoanDto fetchLoan(String mobileNumber);


    boolean updateLoan(LoanDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
