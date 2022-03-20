package com.sarabada.tradingbots.event.listener;

import com.sarabada.tradingbots.event.TickerDataArrivesEvent;
import com.sarabada.tradingbots.service.CurrencyEntryService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TickerDataArrivesListener {

    private CurrencyEntryService currencyEntryService;

    @Async
    @EventListener
    public void on (TickerDataArrivesEvent event) {
        currencyEntryService.save(event.getTickerDataDTO());
    }
}