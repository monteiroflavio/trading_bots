package com.sarabada.tradingbots.controller.validator.impl;

import com.sarabada.tradingbots.controller.validator.MovingAverageConfigValidator;
import com.sarabada.tradingbots.dto.ErrorDTO;
import com.sarabada.tradingbots.dto.MovingAverageConfigDTO;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovingAverageConfigValidatorImpl implements MovingAverageConfigValidator {

    @Override
    public void validateSave(List<MovingAverageConfigDTO> movingAverageConfigs, List<ErrorDTO> errors) {
        for (int i = 0; i < movingAverageConfigs.size(); i++) {
            if (movingAverageConfigs.get(i) == null) {
                errors.add(ErrorDTOFactory.getMissingParameter(String.format("movingAverageConfigs[%s]", i)));
            } else {
                if (movingAverageConfigs.get(i).getType() == null) {
                    errors.add(ErrorDTOFactory.getMissingParameter(String.format("movingAverageConfigs[%s].type", i)));
                }
                if (movingAverageConfigs.get(i).getOperation() == null) {
                    errors.add(ErrorDTOFactory.getMissingParameter(String.format("movingAverageConfigs[%s].operation", i)));
                }
                if (movingAverageConfigs.get(i).getPeriods() == null) {
                    errors.add(ErrorDTOFactory.getMissingParameter(String.format("movingAverageConfigs[%s].periods", i)));
                }
                if (movingAverageConfigs.get(i).getReference() == null) {
                    errors.add(ErrorDTOFactory.getMissingParameter(String.format("movingAverageConfigs[%s].reference", i)));
                }
            }
        }
    }
}