package com.sarabada.tradingbots.controller.validator;

import com.sarabada.tradingbots.dto.ErrorDTO;
import com.sarabada.tradingbots.dto.MovingAverageConfigDTO;

import java.util.List;

public interface MovingAverageConfigValidator {
    void validateSave(List<MovingAverageConfigDTO> movingAverageConfigDTOs, List<ErrorDTO> errors);
}