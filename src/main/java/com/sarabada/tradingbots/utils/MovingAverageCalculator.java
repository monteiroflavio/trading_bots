package com.sarabada.tradingbots.utils;

import com.sarabada.tradingbots.enums.MovingAverageOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

public class MovingAverageCalculator {
    public static BigDecimal calculate(List<BigDecimal> timeWindow, MovingAverageOperation type) {
        BigDecimal movingAverage = BigDecimal.valueOf(0);
        switch (type) {
            case SIMPLE: // weighted and exponential are not active yet ;)
            case WEIGHTED:
            case EXPONENTIAL:
                movingAverage = calculateSimple(timeWindow);
                break;
        }
        return movingAverage;
    }

    private static BigDecimal calculateSimple (List<BigDecimal> timeWindow) {
        return timeWindow.stream()
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(timeWindow.size()), RoundingMode.CEILING);
    }
}