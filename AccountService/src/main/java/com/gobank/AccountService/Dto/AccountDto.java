package com.gobank.AccountService.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Account",description = "holding the infoformation of account"
)
public class AccountDto {
    @Pattern(regexp = "(^$|[0-9]{10})", message = "account number suould be 10 digit ")
    @NotEmpty(message = "Account no not be empty or not null")
    @Schema(example = "**********")
    private Long accountNumber;
    @NotEmpty(message = "Account Type not be empty or not null")
    @Schema(
            description = "Account type of Eazy Bank account", example = "Current/Savings"
    )

    private String accountType;
    @Schema(
            description = "branch address   of Eazy Bank ", example = "place"
    )
    @NotEmpty(message = "branch address not be empty or not null")

    private String branchAddress;
}
