package com.sarabada.tradingbots.mapper;

import com.sarabada.tradingbots.dto.MovingAverageConfigDTO;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.model.MovingAverageConfig;

import java.util.List;

public interface MovingAverageConfigMapper {
    MovingAverageConfig toModel(MovingAverageConfigDTO movingAverageConfigDTO, Bot bot);
    List<MovingAverageConfig> toModel(List<MovingAverageConfigDTO> movingAverageConfigDTOs, Bot bot);
    MovingAverageConfigDTO toDTO(MovingAverageConfig movingAverageConfig);
    List<MovingAverageConfigDTO> toDTO(List<MovingAverageConfig> movingAverageConfigs);
}