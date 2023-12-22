package com.gobank.service.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Cards", description = "Holding the all informatin of all cards holder")
public class CardDto {
    @NotEmpty(message="mobille number not be empty or not null")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @Schema(description = " mobile number of the customer" , example = "9863382344")
    private String mobileNumber;
    @NotEmpty(message="card number not be empty or not null")
    @Pattern(regexp="(^$|[0-9]{12})",message = "Mobile Number must be 12 digits")
    @Schema(description = " card number of the customer" , example = "986823445678")
    private String cardNumber;
    @NotEmpty(message="card type not be empty or not null")
    @Schema(description = " Card type of the customer" , example = "Credit  or Debit")
    private  String cardType;
    @Schema(description = " total amount of the card" , example = "10000000")
    @Column(name = "total_Limit")
    @Positive(message = "Total card limit should be greater than zero")
    private int totalAmount;
    @Schema(description = " total amount  used of the card" , example = "455455")
     @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private  int amountUsed;
    @Schema(description = " total available amount of the card" , example = "899999")
    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")

    private int availableAmount;
}

