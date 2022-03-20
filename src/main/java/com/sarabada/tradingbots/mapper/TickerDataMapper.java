package com.sarabada.tradingbots.mapper;

import com.sarabada.tradingbots.dto.TickerDataDTO;

public interface TickerDataMapper {
    TickerDataDTO toDTO(String message);
}