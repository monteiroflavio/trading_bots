package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.model.CurrencyPeriod;
import com.sarabada.tradingbots.model.MovingAverage;
import com.sarabada.tradingbots.model.MovingAverageConfig;

import java.util.List;
import java.util.Optional;

public interface MovingAverageService {
    Optional<MovingAverage> findByCurrencyPeriodAndTypeAndReferenceAndPeriods(CurrencyPeriod currencyPeriod
            , MovingAverageConfig movingAverageConfig);
    MovingAverage save(CurrencyPeriod currencyPeriod, MovingAverageConfig movingAverageConfig);
    List<MovingAverage> findTwoLastShortMovingAveragesBy(MovingAverageConfig movingAverageConfig, Long id);
    List<MovingAverage> findTwoLastLongMovingAverageBy(MovingAverageConfig movingAverageConfig, List<Long> extractCurrencyPeriodIds);
}