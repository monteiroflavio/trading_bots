package com.sarabada.tradingbots.utils;

import com.sarabada.tradingbots.enums.Entity;
import com.sarabada.tradingbots.enums.Operation;
import com.sarabada.tradingbots.exception.TradingBotsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandler {
    public static TradingBotsException handle(String message, Operation operation, Entity entity) {
        log.error(message);
        return TradingBotsExceptionFactory
                .getInternalServerError(ErrorDTOFactory
                        .getUntreatedException(operation.name(), entity.name()));
    }
}
