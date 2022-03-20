package com.sarabada.tradingbots.mapper.impl;

import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.mapper.CurrencyMapper;
import com.sarabada.tradingbots.model.Currency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyMapperImpl implements CurrencyMapper {
    @Override
    public Currency toModel(CurrencyDTO currencyDTO) {
        return Currency.builder().id(currencyDTO.getId()).currency(currencyDTO.getCurrency()).build();
    }

    @Override
    public List<Currency> toModel(List<CurrencyDTO> currencyDTOs) {
        return currencyDTOs.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public CurrencyDTO toDTO(Currency currency) {
        return CurrencyDTO.builder().id(currency.getId()).currency(currency.getCurrency()).build();
    }

    @Override
    public List<CurrencyDTO> toDTO(List<Currency> currencies) {
        return currencies.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
