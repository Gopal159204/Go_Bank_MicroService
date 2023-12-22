package com.gobank.AccountService.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Loan Dto" , description = "This loan hold the details of customer loan detils")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
    @NotEmpty(message = "mobile number not be empty or not be null value")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @Schema(description = "mobile number of the customer" , example = "1234567890")
    private  String mobileNumber;
    @Schema(description = "loan number of the customer", example = "1234567891")
    @NotEmpty(message = "loan number of the customer not be empty ot not null" )
    @Pattern(regexp = "($|[0-9]{12})" , message = "loan number must be 12 digits")
    private String loanNumber;
    @NotEmpty(message = "loan type not be null and not be empty")
    @Schema(description = "loan type of the customer " , example = "Home/Car")

    private  String loanType;
    @Positive(message = "total loan must be grater than zero")
    @Schema(description = "total loan of the customer" , example = " i.e 4564634")
    private  int totalLoan;
    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(description = "Total loan amount of the customer", example =  " i.e 1233242")
    private  int amountPaid;
    @PositiveOrZero(message = "Total outstanding amount paid should be equal or greater than zero")
    @Schema(description = "Total outstanding amount of the customer", example =  " i.e 1233242")
    private int outstandingAmount;



}
