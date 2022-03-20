package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<CurrencyDTO> list();
    Optional<Currency> findById(Long id);
    List<Currency> findAllByIds(List<Long> ids);
}