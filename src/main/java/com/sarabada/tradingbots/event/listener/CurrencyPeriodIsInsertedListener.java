package com.sarabada.tradingbots.event.listener;

import com.sarabada.tradingbots.event.CurrencyPeriodIsInsertedEvent;
import com.sarabada.tradingbots.event.MovingAverageIsInsertedEvent;
import com.sarabada.tradingbots.model.BotCurrency;
import com.sarabada.tradingbots.model.MovingAverage;
import com.sarabada.tradingbots.service.BotCurrencyService;
import com.sarabada.tradingbots.service.MovingAverageService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CurrencyPeriodIsInsertedListener {

    private BotCurrencyService botCurrencyService;
    private MovingAverageService movingAverageService;
    private ApplicationEventPublisher applicationEventPublisher;

    @Async
    @EventListener
    public void on(CurrencyPeriodIsInsertedEvent event) {
        List<BotCurrency> botCurrencies = botCurrencyService
                .findAllByCurrencyAndInterval(event.getCurrencyPeriod().getCurrency(), event.getCurrencyPeriod().getInterval());
        botCurrencies.forEach(botCurrency -> {
            botCurrency.getBot().getMovingAverageConfigs().forEach(movingAverageConfig -> {
                Optional<MovingAverage> optionalMovingAverage = movingAverageService
                        .findByCurrencyPeriodAndTypeAndReferenceAndPeriods(event.getCurrencyPeriod(), movingAverageConfig);
                if (optionalMovingAverage.isEmpty()) {
                    MovingAverage movingAverage = movingAverageService.save(event.getCurrencyPeriod(), movingAverageConfig);
                    if (movingAverage != null) {
                        applicationEventPublisher.publishEvent(MovingAverageIsInsertedEvent.builder()
                                .movingAverage(movingAverage)
                                .currencyPeriod(event.getCurrencyPeriod())
                                .build());
                    }
                }
            });
        });
    }
}