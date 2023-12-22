package com.gobank.service.Service;

import com.gobank.service.Dto.CardDto;

public interface CardService {


    void createCard(String mobileNumber);


    CardDto fetchCard(String mobileNumber);

    boolean updatedCard(CardDto cardDto);

    boolean deleteCard(String mobileNumber);
}
