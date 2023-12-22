package com.gobank.AccountService.Controller;

import com.gobank.AccountService.Dto.AccountContactInfoDetails;
import com.gobank.AccountService.Dto.CustomerDetailsDto;
import com.gobank.AccountService.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag( name = "Resti api for customer operation",description = "rest api for in gobank to fetch details")
@RestController
@Validated
@EnableConfigurationProperties(value={AccountContactInfoDetails.class})

public class CustomerControllerr {
    private  static  final Logger logger= LoggerFactory.getLogger(CustomerControllerr.class);
    private final ICustomerService iCustomerService;

    public CustomerControllerr(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @Operation(
            summary="Fecting the customer details of the customer",
            description = "we are Fetching the vustomer details of rest api  of the customeer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    }
    )

    @GetMapping("/fetchCustomer")
    public ResponseEntity<CustomerDetailsDto> fetchCustomer
            (
            @RequestHeader("gobank-correlation-id")String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})", message = "account number should be 10 digit ")@RequestParam  String mobileNumber) {
        logger.debug("gobank-correlation-id found: {}",correlationId);
      CustomerDetailsDto customerDetailsDto=  iCustomerService.fetchcustomerDetials(mobileNumber,correlationId);
      return  ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }


    }

