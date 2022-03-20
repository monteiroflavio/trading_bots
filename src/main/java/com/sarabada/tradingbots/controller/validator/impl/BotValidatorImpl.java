package com.sarabada.tradingbots.controller.validator.impl;

import com.sarabada.tradingbots.controller.validator.BotValidator;
import com.sarabada.tradingbots.controller.validator.MovingAverageConfigValidator;
import com.sarabada.tradingbots.dto.BotDTO;
import com.sarabada.tradingbots.dto.ErrorDTO;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class BotValidatorImpl implements BotValidator {

    private MovingAverageConfigValidator movingAverageConfigValidator;

    @Override
    public void validateCreate(BotDTO botDTO) {
        List<ErrorDTO> errors = new ArrayList<>();
        if (botDTO == null) {
            errors.add(ErrorDTOFactory.getMissingParameter("body"));
        } else {
            if (botDTO.getName() == null || botDTO.getName().isBlank()) {
                errors.add(ErrorDTOFactory.getMissingParameter("name"));
            }
            if (botDTO.getInterval() == null) {
                errors.add(ErrorDTOFactory.getMissingParameter("interval"));
            }
            if (botDTO.getOrderQuantity() == null) {
                errors.add(ErrorDTOFactory.getMissingParameter("orderQuantity"));
            }
            if (botDTO.getMovingAverageConfigs() == null) {
                errors.add(ErrorDTOFactory.getMissingParameter("movingAverageConfigs"));
            } else {
                movingAverageConfigValidator.validateSave(botDTO.getMovingAverageConfigs(), errors);
            }
        }
        if (!errors.isEmpty()) {
          throw TradingBotsExceptionFactory.getBadRequest(errors);
        }
    }

    @Override
    public void validateUpdate(Long id) {
        if (id == null) {
            throw TradingBotsExceptionFactory
                    .getBadRequest(Collections.singletonList(ErrorDTOFactory.getMissingParameter("id")));
        }
    }

    @Override
    public void validateDelete(Long id) {
        if (id == null) {
            throw TradingBotsExceptionFactory
                    .getBadRequest(Collections.singletonList(ErrorDTOFactory.getMissingParameter("id")));
        }
    }
}