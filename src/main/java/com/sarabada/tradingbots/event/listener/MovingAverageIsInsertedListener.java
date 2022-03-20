package com.sarabada.tradingbots.event.listener;

import com.sarabada.tradingbots.enums.MovingAverageType;
import com.sarabada.tradingbots.enums.OrderType;
import com.sarabada.tradingbots.event.MovingAverageIsInsertedEvent;
import com.sarabada.tradingbots.model.BotCurrency;
import com.sarabada.tradingbots.model.MovingAverage;
import com.sarabada.tradingbots.model.MovingAverageConfig;
import com.sarabada.tradingbots.service.BotCurrencyService;
import com.sarabada.tradingbots.service.MovingAverageService;
import com.sarabada.tradingbots.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class MovingAverageIsInsertedListener {

    private OrderService orderService;
    private BotCurrencyService botCurrencyService;
    private MovingAverageService movingAverageService;

    @Async
    @EventListener
    public void on(MovingAverageIsInsertedEvent event) {
        List<BotCurrency> botCurrencies = botCurrencyService
                .findAllByCurrencyAndInterval(event.getCurrencyPeriod().getCurrency(), event.getCurrencyPeriod().getInterval());
        botCurrencies.forEach(botCurrency -> {
            MovingAverageConfig movingAverageConfig = getMovingAverageConfigOf(botCurrency.getBot().getMovingAverageConfigs(), MovingAverageType.SHORT);
            List<MovingAverage> shortMovingAverages = movingAverageService
                    .findTwoLastShortMovingAveragesBy(movingAverageConfig, event.getCurrencyPeriod().getCurrency().getId());
            if (hasEnoughOf(shortMovingAverages)) {
                movingAverageConfig = getMovingAverageConfigOf(botCurrency.getBot().getMovingAverageConfigs(), MovingAverageType.LONG);
                List<MovingAverage> longMovingAverages = movingAverageService
                        .findTwoLastLongMovingAverageBy(movingAverageConfig, extractCurrencyPeriodIds(shortMovingAverages));
                if (hasEnoughOf(longMovingAverages)) {
                    if (isPurchasable(shortMovingAverages, longMovingAverages)) {
                        log.info("SIMULATING BUY ORDER REQUEST TO POLONIEX");
                        orderService.save(botCurrency.getBot()
                                , calculatesOrderValue(event.getMovingAverage().getValue(), botCurrency.getBot().getOrderQuantity())
                                , OrderType.PURCHASE);
                    } else if (isSellable(shortMovingAverages, longMovingAverages)) {
                        log.info("SIMULATING SELL ORDER REQUEST TO POLONIEX");
                        orderService.save(botCurrency.getBot()
                                , calculatesOrderValue(event.getMovingAverage().getValue(), botCurrency.getBot().getOrderQuantity())
                                , OrderType.SALE);
                    }
                }
            }
        });
    }

    private BigDecimal calculatesOrderValue(BigDecimal value, Long orderQuantity) {
        return value.multiply(new BigDecimal(orderQuantity));
    }

    private MovingAverageConfig getMovingAverageConfigOf(List<MovingAverageConfig> movingAverageConfigs, MovingAverageType type) {
        return movingAverageConfigs.stream()
                .filter(movingAverageConfig -> movingAverageConfig.getType().equals(type))
                .findFirst()
                .get();
    }

    private boolean isPurchasable(List<MovingAverage> shortMovingAverages, List<MovingAverage> longMovingAverages) {
        return shortMovingAverages.get(0).getValue().compareTo(longMovingAverages.get(0).getValue()) < 0
                && shortMovingAverages.get(1).getValue().compareTo(longMovingAverages.get(1).getValue()) > 0;
    }

    private boolean isSellable(List<MovingAverage> shortMovingAverages, List<MovingAverage> longMovingAverages) {
        return shortMovingAverages.get(0).getValue().compareTo(longMovingAverages.get(0).getValue()) > 0
                && shortMovingAverages.get(1).getValue().compareTo(longMovingAverages.get(1).getValue()) < 0;
    }

    private boolean hasEnoughOf(List<MovingAverage> movingAverages) {
        return movingAverages.size() == 2;
    }

    private List<Long> extractCurrencyPeriodIds(List<MovingAverage> shortMovingAverages) {
        return shortMovingAverages.stream()
                .map(movingAverage -> movingAverage.getCurrencyPeriod().getId())
                .collect(Collectors.toList());
    }
}