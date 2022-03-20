package com.sarabada.tradingbots.controller.advicer;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sarabada.tradingbots.exception.TradingBotsException;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@Slf4j
@ControllerAdvice
public class TradingBotsAdvicer {
    @ExceptionHandler({TradingBotsException.class})
    public ResponseEntity handleTradingBotsException (TradingBotsException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getErrors());
    }

    @ExceptionHandler({InvalidFormatException.class})
    public  ResponseEntity handleInvalidFormatException (InvalidFormatException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonList(ErrorDTOFactory.getMissingParameter("periods.type or status")));
    }
}