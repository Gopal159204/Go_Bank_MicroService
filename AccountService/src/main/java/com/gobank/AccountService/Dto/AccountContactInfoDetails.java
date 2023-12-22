package com.gobank.AccountService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
@ConfigurationProperties(prefix = "accounts")
@Getter
@Data
@Setter
@AllArgsConstructor
public class AccountContactInfoDetails {
    private  String message;
    private Map<String,String> contactDetails;
    private List<String> oncallsupport;
}
