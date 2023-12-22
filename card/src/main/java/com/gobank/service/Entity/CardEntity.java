package com.gobank.service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@Table(name = "card_Service")
@NoArgsConstructor
public class CardEntity extends  BaseEntity{
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
     @GenericGenerator(name="native",strategy = "native")
    private Long cardId;
    private String mobileNumber;
    private String cardNumber;
    private  String cardType;
    private int totalAmount;
    private  int amountUsed;
    private int availableAmount;


}
