package com.sarabada.tradingbots.utils;

import com.sarabada.tradingbots.dto.ErrorDTO;

public class ErrorDTOFactory {
    public static ErrorDTO getMissingParameter(String parameter) {
        return ErrorDTO.builder().message(String.format("Invalid parameter %s", parameter)).build();
    }

    public static ErrorDTO getUntreatedException(String operation, String entity) {
        return ErrorDTO.builder().message(String.format("%s %s fail", operation, entity)).build();
    }

    public static ErrorDTO getNotFound(String entity) {
        return ErrorDTO.builder().message(String.format("%s not found", entity)).build();
    }

    public static ErrorDTO getInvalidParameter(String parameter, String customMessage) {
        return ErrorDTO.builder().message(String.format("Invalid parameter %s. %s", parameter, customMessage)).build();
    }
}