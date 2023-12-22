package com.gobank.service.Mapper;

import com.gobank.service.Dto.CardDto;
import com.gobank.service.Entity.CardEntity;

public class CardMapper {
    public static CardDto mpaTOCardDto(CardDto cardDto, CardEntity cardEntity){
        cardDto.setMobileNumber(cardEntity.getMobileNumber());
        cardDto.setAmountUsed(cardEntity.getAmountUsed());
        cardDto.setAvailableAmount(cardEntity.getAvailableAmount());
        cardDto.setCardNumber(cardEntity.getCardNumber());
        cardDto.setTotalAmount(cardEntity.getTotalAmount());
        cardDto.setCardType(cardEntity.getCardType());
        return  cardDto;
    }

    public static CardEntity mpaTOCard(CardDto cardDto, CardEntity cardEntity){
        cardEntity.setMobileNumber(cardDto.getMobileNumber());
        cardEntity.setAmountUsed(cardDto.getAmountUsed());
        cardEntity.setAvailableAmount(cardDto.getAvailableAmount());
        cardEntity.setCardNumber(cardDto.getCardNumber());
        cardEntity.setTotalAmount(cardDto.getTotalAmount());
        cardEntity.setCardType(cardDto.getCardType());
        return  cardEntity;
    }
}
