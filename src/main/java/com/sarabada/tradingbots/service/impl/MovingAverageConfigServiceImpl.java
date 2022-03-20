package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.dto.MovingAverageConfigDTO;
import com.sarabada.tradingbots.enums.Entity;
import com.sarabada.tradingbots.enums.Operation;
import com.sarabada.tradingbots.mapper.MovingAverageConfigMapper;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.model.MovingAverageConfig;
import com.sarabada.tradingbots.repository.MovingAverageConfigRepository;
import com.sarabada.tradingbots.service.MovingAverageConfigService;
import com.sarabada.tradingbots.utils.ExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovingAverageConfigServiceImpl implements MovingAverageConfigService {

    private MovingAverageConfigMapper mapper;
    private MovingAverageConfigRepository repository;

    @Override
    public List<MovingAverageConfig> saveAll(Bot bot, List<MovingAverageConfigDTO> movingAverageConfigDTOs) {
        try {
            List<MovingAverageConfig> movingAverageConfigs = this.mapper.toModel(movingAverageConfigDTOs, bot);
            return this.repository.saveAll(movingAverageConfigs);
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.SAVE, Entity.MOVING_AVERAGE_CONFIG);
        }
    }
}