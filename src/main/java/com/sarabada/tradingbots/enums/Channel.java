package com.sarabada.tradingbots.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Channel {
    ACCOUNT_NOTIFICATION(1000),
    TICKER_DATA(1002),
    TWENTY_FOUR_HOUR_EXCHANGE_VOLUME(1003),
    HEARTBEAT(1010);
    private Integer channel;
}