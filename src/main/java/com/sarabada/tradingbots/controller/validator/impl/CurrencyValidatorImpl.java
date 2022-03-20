package com.sarabada.tradingbots.controller.validator.impl;

import com.sarabada.tradingbots.controller.validator.CurrencyValidator;
import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.dto.ErrorDTO;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyValidatorImpl implements CurrencyValidator {

    @Override
    public void validateLinkToBot(List<CurrencyDTO> currencyDTOs) {
        List<ErrorDTO> errors = new ArrayList<>();
        if (currencyDTOs == null || currencyDTOs.isEmpty()) {
            errors.add(ErrorDTOFactory.getMissingParameter("[]"));
        } else {
            for (int i = 0; i < currencyDTOs.size(); i++) {
                if (currencyDTOs.get(i) == null) {
                    errors.add(ErrorDTOFactory.getMissingParameter(String.format("[%s]", i)));
                } else {
                    if (currencyDTOs.get(i).getId() == null) {
                        errors.add(ErrorDTOFactory.getMissingParameter(String.format("[%s].id", i)));
                    }
                }
            }
        }
        if (!errors.isEmpty()) {
            throw TradingBotsExceptionFactory.getBadRequest(errors);
        }
    }
}