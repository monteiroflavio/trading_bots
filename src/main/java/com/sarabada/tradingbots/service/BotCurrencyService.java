package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.dto.BotCurrencyDTO;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.model.BotCurrency;
import com.sarabada.tradingbots.model.Currency;

import java.util.List;

public interface BotCurrencyService {
    List<BotCurrency> saveAll(List<BotCurrency> botCurrencies);
    BotCurrencyDTO update(Long botId, Long botCurrencyId, BotCurrencyDTO botCurrencyDTO);
    void delete(Long botId, Long botCurrencyId);
    List<BotCurrency> findAllByBotId(Bot bot);
    List<BotCurrency> findAllByCurrency(Currency currency);
    List<BotCurrency> findAllByCurrencyAndInterval(Currency currency, Integer interval);
}