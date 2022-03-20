package com.sarabada.tradingbots.mapper;

import com.sarabada.tradingbots.dto.TickerDataDTO;
import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.model.CurrencyEntry;

public interface CurrencyEntryMapper {
    CurrencyEntry toModel(TickerDataDTO tickerDataDTO, Currency currency);
}