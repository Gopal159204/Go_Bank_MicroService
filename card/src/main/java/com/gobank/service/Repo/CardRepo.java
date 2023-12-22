package com.gobank.service.Repo;

import com.gobank.service.Entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepo  extends JpaRepository<CardEntity, Long> {
    Optional<CardEntity>findByMobileNumber(String mobileNumber);
    Optional<CardEntity>findByCardNumber(String cardNumber);

}
