package com.gobank.AccountService.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer detials",description = "holding the information of customer,accounts ,loans,card"
)
public class CustomerDetailsDto {
    @NotEmpty(message = "name not null or  empty")
    @Size(min = 5,max = 30,message = "name shoud be between 5 to 30 char only!!")
    @Schema(
            description = "name of customer", example = "Gopal"
    )
    private String  name;
    @NotEmpty(message = "name not null or  empty")
    @NotEmpty
    @Email(message = "email should be valid email")
    @Schema(
            description = "email of customer", example = "xyz@gmail.com"
    )
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})" , message = "moble number should be 10 digit")
    @Schema(
            description = "mobile number of customer", example = "123346767"
    )
    private String mobileNumber;
    @Schema(
            description = "AccountDto of customer", example = "Accounts"
    )
    private  AccountDto accountDto;

    @Schema(
            description = "loandto of customer", example = "Loan"
    )
    private  LoanDto loanDto;
    @Schema(
            description = "cardDto of customer", example = "card"
    )
    private  CardDto cardDto;
}
