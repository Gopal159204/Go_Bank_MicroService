package com.gobank.AccountService.Controller;

import com.gobank.AccountService.Dto.AccountContactInfoDetails;
import com.gobank.AccountService.Dto.CustomerDto;
import com.gobank.AccountService.Dto.ErrorResoponse;
import com.gobank.AccountService.Dto.ResponseDto;
import com.gobank.AccountService.constant.AccountConstant;
import com.gobank.AccountService.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag( name = "Crud operation",description = "we are performing the crud opeation inside the microservice")
@RestController
@Validated
@EnableConfigurationProperties(value={AccountContactInfoDetails.class})
public class AccountController {

    private  final IAccountService iAccountService;
@Value("${build.version}")
private String buildVersion;

@Autowired
private Environment environment;
@Autowired
private AccountContactInfoDetails accountContactInfoDetails;

    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    //public AccountController(IAccountService iAccountService){
//    this.iAccountService=iAccountService;
//
//}
 @Operation(
         summary="create the account of the customer",
         description = "we are creating the aacount of the customeer"
 )
 @ApiResponses({
         @ApiResponse(
                 responseCode = "201",
                 description = "HTTP Status created "
         ),
         @ApiResponse(
                 responseCode = "501",
                 description = "HTTP status internale sevrer error ",
                 content = @Content(
                         schema = @Schema(implementation = ErrorResoponse.class)
                 )
         ),

 })



    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid@RequestBody CustomerDto customerDto) {
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }
    @Operation(
            summary="Fecting the account of the customer",
            description = "we are Fetching the acount of the customeer"
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


    @GetMapping("/get/{mobileNumber}")
    public ResponseEntity<CustomerDto> getAccunt(@Pattern(regexp = "(^$|[0-9]{10})", message = "account number suould be 10 digit ") @PathVariable String mobileNumber) {
        CustomerDto saved = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    //update Account Details
    @Operation(
            summary="update the account of the customer",
            description = "we are updating the aacount of the customeer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status created "
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP status excepton failed  "
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )

    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.upadtedAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }else {
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstant.STATUS_417,AccountConstant.MESSAGE_417_DELETE));
        }

        }
    @Operation(
            summary="delete the account of the customer",
            description = "we are delete the acount of the customeer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status created "
            ),
            @ApiResponse(
                    responseCode = "417",description = "HTTP status geting the error exception "

            ), @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )

    })
        @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto>deleteAccount(@Pattern(regexp = "(^$|[0-9]{10})", message = "account number suould be 10 digit ")@RequestParam String mobileNumber ){
           boolean isdelted=iAccountService.deleteAccount(mobileNumber);
            if (isdelted ) {
                return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200,AccountConstant.MESSAGE_200));


            }
            else {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstant.STATUS_417,AccountConstant.MESSAGE_417_DELETE));
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
                            schema = @Schema(implementation = ErrorResoponse.class)
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
                            schema = @Schema(implementation = ErrorResoponse.class)
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
                            schema = @Schema(implementation = ErrorResoponse.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDetails>getAccountContactInfo(){
        return  ResponseEntity.status(HttpStatus.OK).body(accountContactInfoDetails);
    }


}