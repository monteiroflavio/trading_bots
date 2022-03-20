package com.sarabada.tradingbots.mapper;

import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.model.Currency;

import java.util.List;

public interface CurrencyMapper {
    Currency toModel(CurrencyDTO currencyDTO);
    List<Currency> toModel(List<CurrencyDTO> currencyDTOs);
    CurrencyDTO toDTO(Currency currency);
    List<CurrencyDTO> toDTO(List<Currency> currencies);
}