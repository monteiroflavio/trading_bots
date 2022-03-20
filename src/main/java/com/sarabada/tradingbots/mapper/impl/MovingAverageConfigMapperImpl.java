package com.sarabada.tradingbots.mapper.impl;

import com.sarabada.tradingbots.dto.MovingAverageConfigDTO;
import com.sarabada.tradingbots.mapper.MovingAverageConfigMapper;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.model.MovingAverageConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovingAverageConfigMapperImpl implements MovingAverageConfigMapper {

    @Override
    public MovingAverageConfig toModel(MovingAverageConfigDTO movingAverageConfigDTO, Bot bot) {
        return MovingAverageConfig.builder()
                .bot(bot)
                .operation(movingAverageConfigDTO.getOperation())
                .periods(movingAverageConfigDTO.getPeriods())
                .reference(movingAverageConfigDTO.getReference())
                .type(movingAverageConfigDTO.getType())
                .build();
    }

    @Override
    public List<MovingAverageConfig> toModel(List<MovingAverageConfigDTO> movingAverageConfigDTOs, Bot bot) {
        return movingAverageConfigDTOs.stream().map(mac -> this.toModel(mac, bot)).collect(Collectors.toList());
    }

    @Override
    public MovingAverageConfigDTO toDTO(MovingAverageConfig movingAverageConfig) {
        return MovingAverageConfigDTO.builder()
                .id(movingAverageConfig.getId())
                .operation(movingAverageConfig.getOperation())
                .periods(movingAverageConfig.getPeriods())
                .reference(movingAverageConfig.getReference())
                .type(movingAverageConfig.getType())
                .build();
    }

    @Override
    public List<MovingAverageConfigDTO> toDTO(List<MovingAverageConfig> movingAverageConfigs) {
        return movingAverageConfigs.stream().map(this::toDTO).collect(Collectors.toList());
    }
}