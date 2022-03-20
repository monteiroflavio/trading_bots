package com.sarabada.tradingbots.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Entity {
    BOT,
    ORDER,
    CURRENCY,
    BOT_CURRENCY,
    CURRENCY_ENTRY,
    CURRENCY_PERIOD,
    MOVING_AVERAGE_CONFIG;
}