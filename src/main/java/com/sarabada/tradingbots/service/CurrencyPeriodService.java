package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.model.CurrencyEntry;
import com.sarabada.tradingbots.model.CurrencyPeriod;

import java.util.List;
import java.util.Optional;

public interface CurrencyPeriodService {
    Optional<CurrencyPeriod> findLastPeriodOfCurrencyToInterval(Currency currency, Integer interval);
    void save(List<CurrencyEntry> currencyEntries, Integer interval, Integer sequence);
    List<CurrencyPeriod> findAllByCurrencyAfterSequence(Currency currency, Integer sequence);
}