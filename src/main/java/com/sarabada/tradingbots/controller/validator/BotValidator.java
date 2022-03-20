package com.sarabada.tradingbots.controller.validator;

import com.sarabada.tradingbots.dto.BotDTO;

public interface BotValidator {
    void validateCreate(BotDTO botDTO);
    void validateUpdate(Long id);
    void validateDelete(Long id);
}