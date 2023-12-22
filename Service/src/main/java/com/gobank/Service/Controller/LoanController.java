package com.gobank.Service.Controller;

import com.gobank.Service.Contsant.LoanConstant;
import com.gobank.Service.Dto.ErrorResponse;
import com.gobank.Service.Dto.LoanContactInfoDetailsDto;
import com.gobank.Service.Dto.LoanDto;
import com.gobank.Service.Dto.ResponseDto;
import com.gobank.Service.Service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/loan")
@EnableConfigurationProperties(value = {LoanContactInfoDetailsDto.class})
@Tag(name="CRUD REST APIs for Loans in EazyBank" , description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details\"")
public class LoanController {
    private  static  final Logger logger= LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private final ILoanService iLoanService;
    @Autowired
    private  LoanContactInfoDetailsDto loanContactInfoDetailsDto;
    @Value("${build.version}")
    private String buildVersion;
    @Autowired
    private Environment environment;

    public LoanController(ILoanService iLoanService) {
        this.iLoanService = iLoanService;
    }

    @Operation(
            summary = "create  loan rest api ",
            description = "to create  rest api for customer "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http status created"
            ),
            @ApiResponse(
                    responseCode = "501",
                    description = "HTTP Status Internal Server Error",
                    content =  @Content(
                            schema = @Schema(implementation = ErrorResponse.class)

                    )

            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto>createLoan(@RequestParam String mobileNumber){
        iLoanService.createLoan(mobileNumber);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstant.STATUS_201,LoanConstant.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
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

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto>fetchLoan(@RequestHeader("gobank-correlation-id")String correlationId,

                                            @RequestParam String mobileNumber){
        logger.debug("gobank-correlation-id found: {}",correlationId);

        LoanDto loanDto= iLoanService.fetchLoan(mobileNumber);
       return  ResponseEntity.status(HttpStatus.OK).body(loanDto);
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @PutMapping("/update")
    public  ResponseEntity<ResponseDto>updateLoan(@RequestBody  LoanDto loanDto){
        boolean isUpdated=iLoanService.updateLoan(loanDto);
        if (isUpdated){
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200) );

        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstant.STATUS_417, LoanConstant.MESSAGE_417_UPDATE) );
        }
    }
    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ResponseDto>deleteLoan(@PathVariable String mobileNumber){
           boolean isDeleated=iLoanService.deleteLoan(mobileNumber);
        if (isDeleated){
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200) );

        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstant.STATUS_417, LoanConstant.MESSAGE_417_UPDATE) );
        }
    }
    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into accounts microservice"
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
    @GetMapping("/build-info")
    public ResponseEntity<String>getBuildInfo(){
        return  ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }
    @Operation(
            summary = "Get  information of java version",
            description = "Get details information where java version has be install in local system"
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
    @GetMapping("/java-version")
    public ResponseEntity<String>getJavaVersion(){
        return  ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }
    @Operation(
            summary = "Get  information of  acounts",
            description = "Get details information wof account details here"
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
    @GetMapping("/contact-info")
    public ResponseEntity<LoanContactInfoDetailsDto>getAccountContactInfo(){
        return  ResponseEntity.status(HttpStatus.OK).body(loanContactInfoDetailsDto);
    }

}
