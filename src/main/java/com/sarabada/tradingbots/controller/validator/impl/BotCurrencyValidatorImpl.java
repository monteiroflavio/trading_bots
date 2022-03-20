package com.sarabada.tradingbots.controller.validator.impl;

import com.sarabada.tradingbots.controller.validator.BotCurrencyValidator;
import com.sarabada.tradingbots.dto.BotCurrencyDTO;
import com.sarabada.tradingbots.dto.ErrorDTO;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotCurrencyValidatorImpl implements BotCurrencyValidator {

    @Override
    public void validateUpdate(Long botId, Long botCurrencyId, BotCurrencyDTO botCurrencyDTO) {
        List<ErrorDTO> errors = new ArrayList<>();
        validateBotIdAndBotCurrencyId(botId, botCurrencyId, errors);
        if (botCurrencyDTO == null) {
            errors.add(ErrorDTOFactory.getMissingParameter("body"));
        } else {
            if (botCurrencyDTO.getStatus() == null) {
                errors.add(ErrorDTOFactory.getMissingParameter("status"));
            }
        }
        if (!errors.isEmpty()) {
            throw TradingBotsExceptionFactory.getBadRequest(errors);
        }
    }

    @Override
    public void validateDelete(Long botId, Long botCurrencyId) {
        List<ErrorDTO> errors = new ArrayList<>();
        validateBotIdAndBotCurrencyId(botId, botCurrencyId, errors);
        if (!errors.isEmpty()) {
            throw TradingBotsExceptionFactory.getBadRequest(errors);
        }
    }

    private void validateBotIdAndBotCurrencyId(Long botId, Long botCurrencyId, List<ErrorDTO> errors) {
        if (botId == null) {
            errors.add(ErrorDTOFactory.getMissingParameter("botId"));
        }
        if (botCurrencyId == null) {
            errors.add(ErrorDTOFactory.getMissingParameter("botCurrencyId"));
        }
    }

}