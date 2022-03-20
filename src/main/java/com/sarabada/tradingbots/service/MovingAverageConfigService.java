package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.dto.MovingAverageConfigDTO;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.model.MovingAverageConfig;

import java.util.List;

public interface MovingAverageConfigService {
    List<MovingAverageConfig> saveAll(Bot bot, List<MovingAverageConfigDTO> movingAverageConfigDTOs);
}