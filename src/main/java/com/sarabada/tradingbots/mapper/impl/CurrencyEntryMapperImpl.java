package com.sarabada.tradingbots.mapper.impl;

import com.sarabada.tradingbots.dto.TickerDataDTO;
import com.sarabada.tradingbots.mapper.CurrencyEntryMapper;
import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.model.CurrencyEntry;
import org.springframework.stereotype.Component;

@Component
public class CurrencyEntryMapperImpl implements CurrencyEntryMapper {
    @Override
    public CurrencyEntry toModel(TickerDataDTO tickerDataDTO, Currency currency) {
        return CurrencyEntry.builder()
                .value(tickerDataDTO.getLastTradePrice())
                .currency(currency)
                .entryDatetime(tickerDataDTO.getMessageTime())
                .build();
    }
}