package com.sarabada.tradingbots.controller.validator;

import com.sarabada.tradingbots.dto.CurrencyDTO;

import java.util.List;

public interface CurrencyValidator {
    void validateLinkToBot(List<CurrencyDTO> currencyDTOs);
}