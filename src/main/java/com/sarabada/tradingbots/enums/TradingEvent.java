package com.sarabada.tradingbots.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TradingEvent {
    LONG_AND_SHORT_ARE_EQUAL,
    LONG_MOVING_AVERAGE_IS_HIGHER,
    SHORT_MOVING_AVERAGE_IS_HIGHER;
}