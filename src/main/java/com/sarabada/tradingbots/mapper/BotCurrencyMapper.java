package com.sarabada.tradingbots.mapper;

import com.sarabada.tradingbots.dto.BotCurrencyDTO;
import com.sarabada.tradingbots.model.BotCurrency;

public interface BotCurrencyMapper {
    BotCurrencyDTO toDTO(BotCurrency botCurrency);
}