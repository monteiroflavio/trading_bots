package com.sarabada.tradingbots.utils;

import com.sarabada.tradingbots.dto.ErrorDTO;
import com.sarabada.tradingbots.exception.TradingBotsException;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class TradingBotsExceptionFactory {
    public static TradingBotsException getBadRequest(List<ErrorDTO> errors) {
        return TradingBotsException.builder().errors(errors).status(HttpStatus.BAD_REQUEST).build();
    }

    public static TradingBotsException getBadRequest(ErrorDTO error) {
        return TradingBotsException.builder().errors(Collections.singletonList(error)).status(HttpStatus.BAD_REQUEST).build();
    }

    public static TradingBotsException getInternalServerError(ErrorDTO error) {
        return TradingBotsException.builder()
                .errors(Collections.singletonList(error))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    public static TradingBotsException getNotFound(ErrorDTO error) {
        return TradingBotsException.builder()
                .errors(Collections.singletonList(error))
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
