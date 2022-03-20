package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.dto.BotDTO;
import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.model.Bot;

import java.util.List;
import java.util.Optional;

public interface BotService {
    BotDTO create(BotDTO botDTO);
    BotDTO linkCurrencies(Long botId, List<CurrencyDTO> currencyDTOs);
    List<BotDTO> list();
    BotDTO update(Long id, BotDTO botDTO);
    void delete(Long id);
    Bot save(Bot bot);
    Optional<Bot> findById(Long id);
}