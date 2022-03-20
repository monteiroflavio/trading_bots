package com.sarabada.tradingbots.mapper.impl;

import com.sarabada.tradingbots.dto.BotCurrencyDTO;
import com.sarabada.tradingbots.dto.BotDTO;
import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.enums.BotCurrencyStatus;
import com.sarabada.tradingbots.mapper.BotMapper;
import com.sarabada.tradingbots.mapper.MovingAverageConfigMapper;
import com.sarabada.tradingbots.model.Bot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BotMapperImpl implements BotMapper {

    private MovingAverageConfigMapper movingAverageConfigMapper;

    @Override
    public Bot toModel(BotDTO botDTO) {
        return Bot.builder()
                .name(botDTO.getName())
                .interval(botDTO.getInterval())
                .orderQuantity(botDTO.getOrderQuantity())
                .build();
    }

    @Override
    public List<Bot> toModel(List<BotDTO> botDTOs) {
        return botDTOs.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public BotDTO toDTO(Bot bot) {
        return BotDTO.builder()
                .id(bot.getId())
                .name(bot.getName())
                .interval(bot.getInterval())
                .movingAverageConfigs(movingAverageConfigMapper.toDTO(bot.getMovingAverageConfigs()))
                .orderQuantity(bot.getOrderQuantity())
                .currencies(bot.getBotCurrencies() != null
                        ? bot.getBotCurrencies().stream()
                            .map(bc -> BotCurrencyDTO.builder()
                                    .currency(CurrencyDTO.builder()
                                            .id(bc.getCurrency().getId())
                                            .currency(bc.getCurrency().getCurrency())
                                        .build())
                                    .id(bc.getId())
                                    .status(bc.getStatus() != null ? bc.getStatus() : BotCurrencyStatus.DISABLED)
                                .build())
                            .collect(Collectors.toList())
                        : null)
                .build();
    }

    @Override
    public List<BotDTO> toDTO(List<Bot> bots) {
        return bots.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Bot toUpdatableModel(Bot bot, BotDTO botDTO) {
        bot.setName(botDTO.getName() != null ? botDTO.getName() : bot.getName());
        bot.setInterval(botDTO.getInterval() != null ? botDTO.getInterval() : bot.getInterval());
        bot.setOrderQuantity(botDTO.getOrderQuantity() != null ? botDTO.getOrderQuantity() : bot.getOrderQuantity());
        return bot;
    }
}