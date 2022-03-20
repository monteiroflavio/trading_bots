package com.sarabada.tradingbots.mapper.impl;

import com.sarabada.tradingbots.dto.BotCurrencyDTO;
import com.sarabada.tradingbots.mapper.BotCurrencyMapper;
import com.sarabada.tradingbots.mapper.BotMapper;
import com.sarabada.tradingbots.mapper.CurrencyMapper;
import com.sarabada.tradingbots.model.BotCurrency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BotCurrencyMapperImpl implements BotCurrencyMapper {

    private BotMapper botMapper;
    private CurrencyMapper currencyMapper;

    @Override
    public BotCurrencyDTO toDTO(BotCurrency botCurrency) {
        return BotCurrencyDTO.builder()
                .id(botCurrency.getId())
                .bot(botMapper.toDTO(botCurrency.getBot()))
                .currency(currencyMapper.toDTO(botCurrency.getCurrency()))
                .status(botCurrency.getStatus())
                .build();
    }
}