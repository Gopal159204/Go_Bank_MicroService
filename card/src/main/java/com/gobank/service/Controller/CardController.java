package com.gobank.service.Controller;

import com.gobank.service.Constant.CardConstant;
import com.gobank.service.Dto.CardContactInfoDetailsDto;
import com.gobank.service.Dto.CardDto;
import com.gobank.service.Dto.ErrorResponseDto;
import com.gobank.service.Dto.ResponseDto;
import com.gobank.service.Service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(
        name = "CRUD REST APIs for Cards in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping("/card")
@Validated
@EnableConfigurationProperties(value={CardContactInfoDetailsDto.class})

public class CardController {
    private  static  final Logger logger= LoggerFactory.getLogger(CardController.class);

    @Autowired
    private final CardService cardService;
    @Value("${build.version}")
    private String buildVersion;
    @Autowired
    private Environment environment;
    @Autowired
    private CardContactInfoDetailsDto cardContactInfoDetailsDto;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard( @Valid @RequestParam String mobileNumber){
        cardService.createCard(mobileNumber);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardConstant.STATUS_201,CardConstant.MESSAGE_201));

    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/get/{mobileNumber}")
    public  ResponseEntity<CardDto>getCardWithMobileNumber(@RequestHeader("gobank-correlation-id")String correlationId,@PathVariable String mobileNumber){
        logger.debug("gobank-correlation-id found: {}",correlationId);

        CardDto card =cardService.fetchCard(mobileNumber);
        return  ResponseEntity.status(HttpStatus.OK).body(card);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/update")
    public  ResponseEntity<ResponseDto>updatedCard( @Valid @RequestBody CardDto cardDto){
        boolean isUpdated=cardService.updatedCard(cardDto);
        if (isUpdated ) {
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstant.STATUS_200,CardConstant.MESSAGE_200));


        }
        else {
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstant.STATUS_417,CardConstant.MESSAGE_417_UPDATE));

        }

    }
    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ResponseDto>deleteCard(@PathVariable String mobileNumber){
        boolean isDeleted= cardService.deleteCard(mobileNumber);
        if (isDeleted){
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstant.STATUS_200,CardConstant.MESSAGE_200));


        }
        else {
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstant.STATUS_417,CardConstant.MESSAGE_417_DELETE));

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
                            schema = @Schema(implementation = ErrorResponseDto.class)
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<CardContactInfoDetailsDto>getAccountContactInfo(){
        return  ResponseEntity.status(HttpStatus.OK).body(cardContactInfoDetailsDto);
    }

}
