package com.sarabada.tradingbots.controller.validator.impl;

import com.sarabada.tradingbots.controller.validator.OrderValidator;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class OrderValidatorImpl implements OrderValidator {

    @Override
    public void validateListByBotId(Long botId) {
        if (botId == null) {
            throw TradingBotsExceptionFactory
                    .getBadRequest(Collections.singletonList(ErrorDTOFactory.getMissingParameter("id")));
        }
    }
}