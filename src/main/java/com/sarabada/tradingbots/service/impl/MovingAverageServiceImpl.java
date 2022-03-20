package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.enums.MovingAverageOperation;
import com.sarabada.tradingbots.enums.PeriodReference;
import com.sarabada.tradingbots.model.CurrencyPeriod;
import com.sarabada.tradingbots.model.MovingAverage;
import com.sarabada.tradingbots.model.MovingAverageConfig;
import com.sarabada.tradingbots.repository.MovingAverageRepository;
import com.sarabada.tradingbots.service.CurrencyPeriodService;
import com.sarabada.tradingbots.service.MovingAverageService;
import com.sarabada.tradingbots.utils.MovingAverageCalculator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovingAverageServiceImpl implements MovingAverageService {

    private MovingAverageRepository repository;
    private CurrencyPeriodService currencyPeriodService;

    @Override
    public Optional<MovingAverage> findByCurrencyPeriodAndTypeAndReferenceAndPeriods(CurrencyPeriod currencyPeriod
            , MovingAverageConfig movingAverageConfig) {
        return repository.findByCurrencyPeriodAndTypeAndReferenceAndPeriods(currencyPeriod, movingAverageConfig.getOperation()
                , movingAverageConfig.getReference(), movingAverageConfig.getPeriods());
    }

    @Override
    public MovingAverage save(CurrencyPeriod currencyPeriod, MovingAverageConfig movingAverageConfig) {
        if (isValidPeriod(movingAverageConfig.getOperation(), currencyPeriod.getSequence(), movingAverageConfig.getPeriods())) {
            List<CurrencyPeriod> currencyPeriods = currencyPeriodService
                    .findAllByCurrencyAfterSequence(currencyPeriod.getCurrency()
                            , currencyPeriod.getSequence() - movingAverageConfig.getPeriods());
            BigDecimal movingAverageValue = MovingAverageCalculator
                    .calculate(extractValuesByReference(currencyPeriods, movingAverageConfig.getReference())
                    , movingAverageConfig.getOperation());
            return repository.save(MovingAverage.builder()
                    .currencyPeriod(currencyPeriod)
                    .operation(movingAverageConfig.getOperation())
                    .reference(movingAverageConfig.getReference())
                    .periods(movingAverageConfig.getPeriods())
                    .value(movingAverageValue)
                    .build());
        }
        return null;
    }

    @Override
    public List<MovingAverage> findTwoLastShortMovingAveragesBy(MovingAverageConfig movingAverageConfig, Long currencyId) {
        return repository.findTwoLastShortMovingAveragesBy(movingAverageConfig.getPeriods(), movingAverageConfig.getOperation()
                , movingAverageConfig.getReference(), currencyId, PageRequest.of(0, 2));
    }

    @Override
    public List<MovingAverage> findTwoLastLongMovingAverageBy(MovingAverageConfig movingAverageConfig, List<Long> currencyPeriodIds) {
        return repository.findTwoLastLongMovingAverageBy(movingAverageConfig.getPeriods(), movingAverageConfig.getOperation()
                , movingAverageConfig.getReference(), currencyPeriodIds, PageRequest.of(0, 2));
    }

    private List<BigDecimal> extractValuesByReference(List<CurrencyPeriod> currencyPeriods, PeriodReference reference) {
        List<BigDecimal> values = null;
        switch (reference) {
            case LOW:
                values = currencyPeriods.stream().map(CurrencyPeriod::getLowValue).collect(Collectors.toList());
                break;
            case CLOSE:
                values = currencyPeriods.stream().map(CurrencyPeriod::getCloseValue).collect(Collectors.toList());
                break;
            case HIGH:
                values = currencyPeriods.stream().map(CurrencyPeriod::getHighValue).collect(Collectors.toList());
                break;
            case OPEN:
                values = currencyPeriods.stream().map(CurrencyPeriod::getOpenValue).collect(Collectors.toList());
                break;
        }
        return values;
    }

    private boolean isValidPeriod(MovingAverageOperation operation, Integer sequence, Integer periods) {
        boolean isValid = false;
        switch (operation) {
            case SIMPLE: // weighted and exponential are not active yet ;)
            case WEIGHTED:
            case EXPONENTIAL:
                isValid = sequence >= periods;
                break;
        }
        return isValid;
    }
}