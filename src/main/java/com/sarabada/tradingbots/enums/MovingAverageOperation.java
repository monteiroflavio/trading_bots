package com.sarabada.tradingbots.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovingAverageOperation {
    EXPONENTIAL,
    SIMPLE,
    WEIGHTED;
}