package com.sarabada.tradingbots.mapper.impl;

import com.sarabada.tradingbots.mapper.CurrencyPeriodMapper;
import com.sarabada.tradingbots.model.CurrencyEntry;
import com.sarabada.tradingbots.model.CurrencyPeriod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CurrencyPeriodMapperImpl implements CurrencyPeriodMapper {

    @Override
    public CurrencyPeriod toModel(List<CurrencyEntry> currencyEntries, Integer interval, Integer sequence) {
        CurrencyEntry openingCurrencyEntry = findOpening(currencyEntries);
        CurrencyEntry closingCurrencyEntry = findClosing(currencyEntries);
        return CurrencyPeriod.builder()
                .closeValue(closingCurrencyEntry.getValue())
                .openValue(openingCurrencyEntry.getValue())
                .currency(openingCurrencyEntry.getCurrency())
                .currencyEntryEnd(closingCurrencyEntry)
                .currencyEntryStart(openingCurrencyEntry)
                .highValue(findHighest(currencyEntries))
                .lowValue(findLowest(currencyEntries))
                .interval(interval)
                .sequence(sequence)
                .build();
    }

    private CurrencyEntry findOpening(List<CurrencyEntry> currencyEntries) {
        return currencyEntries.get(0);
    }

    private CurrencyEntry findClosing(List<CurrencyEntry> currencyEntries) {
        return currencyEntries.get(currencyEntries.size() - 1);
    }

    private BigDecimal findLowest(List<CurrencyEntry> currencyEntries) {
        return currencyEntries.stream().map(CurrencyEntry::getValue).min(BigDecimal::compareTo).orElse(null);
    }

    private BigDecimal findHighest(List<CurrencyEntry> currencyEntries) {
        return currencyEntries.stream().map(CurrencyEntry::getValue).max(BigDecimal::compareTo).orElse(null);
    }
}