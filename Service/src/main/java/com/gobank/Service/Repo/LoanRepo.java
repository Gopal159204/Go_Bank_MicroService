package com.gobank.Service.Repo;

import com.gobank.Service.Entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepo extends JpaRepository<LoanEntity,Long> {
    Optional<LoanEntity> findByMobileNumber(String mobileNumber);

    Optional<LoanEntity> findByLoanNumber(String loanNumber);

}
