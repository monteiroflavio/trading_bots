package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.model.Bot;

public interface BotsPoolService {
    void add (Bot bot);
    void remove (Long id);
    void run(CurrencyDTO currency);
}