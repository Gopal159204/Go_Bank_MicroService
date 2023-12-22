package com.gobank.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
@Data
@Getter
@Setter
@AllArgsConstructor
@ConfigurationProperties(prefix = "cards")
public class CardContactInfoDetailsDto {
    private  String message;
    private Map<String,String> contactDetails;
    private List<String> oncallsupport;
}
