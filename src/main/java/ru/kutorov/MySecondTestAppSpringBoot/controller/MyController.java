package ru.kutorov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kutorov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kutorov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.kutorov.MySecondTestAppSpringBoot.model.*;
import ru.kutorov.MySecondTestAppSpringBoot.service.*;
import ru.kutorov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final AnnualBonusService annualBonusService;
    private final QuarterBonusService quarterBonusService;


    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        AnnualBonusService annualBonusService,
                        QuarterBonusService quarterBonusService){
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.annualBonusService = annualBonusService;
        this.quarterBonusService = quarterBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        log.info("create response: {}", response);


        try{
            validationService.isValid(bindingResult);
            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException("Исключение uid = 123");
            }
        } catch (ValidationFailedException e) {
            return buildErrorResponse(response, ErrorCodes.VALIDATION_EXCEPTION,
                    ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST, "error validation");
        } catch (UnsupportedCodeException e){
            return buildErrorResponse(response, ErrorCodes.UNSUPPORTED_EXCEPTION,
                    ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST, "unsupported error");
        } catch (Exception e){
            return buildErrorResponse(response, ErrorCodes.UNKNOWN_EXCEPTION,
                    ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, "unknown error");
        }
        calculateBonuses(request, response);

        modifyResponseService.modify(response);
        log.info("sent response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void calculateBonuses(Request request, Response response) {
        double annualBonus = annualBonusService.calculate(
                request.getPositions(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays()
        );
        response.setAnnualBonus(annualBonus);
        log.info("Годовая премия: {}", annualBonus);

        double quarterBonus = quarterBonusService.calculate(
                request.getPositions(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays()
        );
        response.setQuarterBonus(quarterBonus);
        log.info("Квартальная премия: {}", quarterBonus);
    }

    private ResponseEntity<Response> buildErrorResponse(Response response,
                                                        ErrorCodes errorCode,
                                                        ErrorMessages errorMessage,
                                                        HttpStatus status,
                                                        String logMessage) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        log.error("{}: {}", logMessage, response);
        return new ResponseEntity<>(response, status);
    }
}
