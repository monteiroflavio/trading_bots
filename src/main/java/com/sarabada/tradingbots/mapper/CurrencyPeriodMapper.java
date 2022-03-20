package com.sarabada.tradingbots.mapper;

import com.sarabada.tradingbots.model.CurrencyEntry;
import com.sarabada.tradingbots.model.CurrencyPeriod;

import java.util.List;

public interface CurrencyPeriodMapper {
    CurrencyPeriod toModel(List<CurrencyEntry> currencyEntries, Integer interval, Integer sequence);
}