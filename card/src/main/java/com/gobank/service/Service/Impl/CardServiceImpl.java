package com.gobank.service.Service.Impl;

import com.gobank.service.Constant.CardConstant;
import com.gobank.service.Dto.CardDto;
import com.gobank.service.Entity.CardEntity;
import com.gobank.service.Exception.CardAlreadyPresnt;
import com.gobank.service.Exception.ResouceNotFoundException;
import com.gobank.service.Mapper.CardMapper;
import com.gobank.service.Repo.CardRepo;
import com.gobank.service.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service

public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepo cardRepo;
    @Override
    public void createCard(String mobileNumber) {
        Optional<CardEntity>cardentiyts =cardRepo.findByMobileNumber(mobileNumber);
        if (cardentiyts.isPresent()){
            throw  new CardAlreadyPresnt("card alread registed with the given mobile number"+mobileNumber);

        }
        cardRepo.save(createNewCard(mobileNumber));

    }

    protected CardEntity createNewCard(String mobileNumber){
        CardEntity cards=new CardEntity();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        cards.setCardNumber(Long.toString(randomCardNumber));
        cards.setMobileNumber(mobileNumber);
        cards.setCardType(CardConstant.CREDIT_CARD);
        cards.setTotalAmount(CardConstant.NEW_CARD_LIMIT);
        cards.setAmountUsed(0);

        cards.setAvailableAmount(CardConstant.NEW_CARD_LIMIT);
        return  cards;
    }


    @Override
    public CardDto fetchCard(String mobileNumber) {
       CardEntity cards= cardRepo.findByMobileNumber(mobileNumber).orElseThrow(()->new ResouceNotFoundException("Card is  not present with given mobile number", "Mobile Number", mobileNumber));
        return CardMapper.mpaTOCardDto(new CardDto(), cards);
    }

    @Override
    public boolean updatedCard(CardDto cardDto) {
        CardEntity cards=cardRepo.findByCardNumber(cardDto.getCardNumber()).orElseThrow(()-> new ResouceNotFoundException("card is not upated with given card number", "Card number", cardDto.getCardNumber()));
             CardMapper.mpaTOCard(cardDto,  cards);
             cardRepo.save(cards);
        return true;
    }


    @Override
    public boolean deleteCard(String mobileNumber) {
        CardEntity cards=cardRepo.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResouceNotFoundException("card is sucessfully deleted with given mobile number", "mobile number", mobileNumber));
        cardRepo.deleteById(cards.getCardId());

        return true;
    }
}
