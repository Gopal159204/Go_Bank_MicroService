package com.gobank.Service.Service.Impl;

import com.gobank.Service.Dto.LoanDto;
import com.gobank.Service.Entity.LoanEntity;
import com.gobank.Service.Exception.LoanAlreadyPresent;
import com.gobank.Service.Repo.LoanRepo;
import com.gobank.Service.Contsant.LoanConstant;
import com.gobank.Service.Exception.ResourceNotFoundException;
import com.gobank.Service.Mapper.LoanMapper;
import com.gobank.Service.Service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl  implements ILoanService {
    @Autowired
    private LoanRepo loanRepo;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<LoanEntity> optionalLoans= loanRepo.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyPresent("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loanRepo.save(createNewLoan(mobileNumber));
    }
    private LoanEntity createNewLoan(String mobileNumber) {
        LoanEntity newLoan = new LoanEntity();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
        return newLoan;
    }


    @Override
    public LoanDto fetchLoan(String mobileNumber) {
       LoanEntity loansEntity=loanRepo.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Not found", "mobileNUmber" , mobileNumber));
        return LoanMapper.mapToLoandDto(loansEntity, new LoanDto());


    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        LoanEntity loanEntity=loanRepo.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(()->new ResourceNotFoundException("loan  number not found this number", "loannoumber", loanDto.getLoanNumber()));
         LoanMapper.mapToLoanEn(loanEntity,loanDto);
         loanRepo.save(loanEntity);
         return  true;

    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        LoanEntity loanEntity=loanRepo.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan","mobile Number",mobileNumber));
            loanRepo.deleteById(loanEntity.getLoanId());
            return  true;
    }
}
