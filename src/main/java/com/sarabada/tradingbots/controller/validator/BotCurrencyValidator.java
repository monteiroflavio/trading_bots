package com.sarabada.tradingbots.controller.validator;

import com.sarabada.tradingbots.dto.BotCurrencyDTO;

public interface BotCurrencyValidator {
    void validateUpdate(Long botId, Long botCurrencyId, BotCurrencyDTO botCurrencyDTO);
    void validateDelete(Long botId, Long botCurrencyId);
}