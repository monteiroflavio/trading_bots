package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.dto.TickerDataDTO;
import com.sarabada.tradingbots.model.CurrencyEntry;

import java.util.List;

public interface CurrencyEntryService {
    CurrencyEntry save(TickerDataDTO tickerDataDTO);
    List<CurrencyEntry> findAllEntriesOrderedAfter(CurrencyEntry currencyEntry);
}