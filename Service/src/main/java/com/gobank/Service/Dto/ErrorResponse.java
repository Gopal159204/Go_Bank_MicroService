package com.gobank.Service.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name="Error_Response", description = "schema hold the error information of the customer")

public class ErrorResponse {
    @Schema(description = "API path invoked by client")
    private String apiPath;
    @Schema(description = "Error code representing the error happened")

    private HttpStatus httpStatus;
    @Schema(description = "Error message representing the error happened")

    private String errorMessage;
    @Schema(description = "Time representing when the error happened")

    private LocalDateTime localDateTime;


}
