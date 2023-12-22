package com.gobank.service.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(
            description = "API path invoked by client"
    )
    private String ApiPath;
    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus httpStatus;
    @Schema(
            description = "Error message representing the error happened"
    )
    private LocalDateTime localDateTime;
    @Schema(
            description = "Time representing when the error happened"
    )
    private String errorMessage;
}
