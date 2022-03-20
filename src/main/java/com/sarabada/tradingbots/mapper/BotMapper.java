package com.sarabada.tradingbots.mapper;

import com.sarabada.tradingbots.dto.BotDTO;
import com.sarabada.tradingbots.model.Bot;

import java.util.List;

public interface BotMapper {
    Bot toModel (BotDTO botDTO);
    List<Bot> toModel (List<BotDTO> botDTO);
    BotDTO toDTO (Bot bot);
    List<BotDTO> toDTO (List<Bot> bot);
    Bot toUpdatableModel (Bot bot, BotDTO botDTO);
}