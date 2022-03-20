package com.sarabada.tradingbots.event.listener;

import com.sarabada.tradingbots.event.CurrencyEntryIsInsertedEvent;
import com.sarabada.tradingbots.model.BotCurrency;
import com.sarabada.tradingbots.model.CurrencyEntry;
import com.sarabada.tradingbots.model.CurrencyPeriod;
import com.sarabada.tradingbots.service.BotCurrencyService;
import com.sarabada.tradingbots.service.CurrencyEntryService;
import com.sarabada.tradingbots.service.CurrencyPeriodService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CurrencyEntryIsInsertedListener {

    private BotCurrencyService botCurrencyService;
    private CurrencyEntryService currencyEntryService;
    private CurrencyPeriodService currencyPeriodService;

    @Async
    @EventListener
    public void on(CurrencyEntryIsInsertedEvent event) {
        List<BotCurrency> botsCurrencies = botCurrencyService.findAllByCurrency(event.getCurrencyEntry().getCurrency());
        botsCurrencies.forEach(botCurrency -> {
            Optional<CurrencyPeriod> optionalCurrencyPeriod = currencyPeriodService
                    .findLastPeriodOfCurrencyToInterval(event.getCurrencyEntry().getCurrency(), botCurrency.getBot().getInterval());
            List<CurrencyEntry> currencyEntries = currencyEntryService
                    .findAllEntriesOrderedAfter(optionalCurrencyPeriod
                            .map(CurrencyPeriod::getCurrencyEntryEnd)
                            .orElse(CurrencyEntry.builder()
                                    .currency(event.getCurrencyEntry().getCurrency())
                                    .build()));
            int initialIndex = 0;
            int sequence = optionalCurrencyPeriod
                    .map(currencyPeriod -> currencyPeriod.getSequence() + 1)
                    .orElse(1);
            for (int i = 0; i < currencyEntries.size(); i++) {
                if (currencyEntries.get(i).getEntryDatetime().getTime()
                        - currencyEntries.get(initialIndex).getEntryDatetime().getTime()
                        > 60000 * botCurrency.getBot().getInterval()) {
                    this.currencyPeriodService.save(currencyEntries.subList(initialIndex, i)
                            , botCurrency.getBot().getInterval()
                            , sequence);
                    sequence++;
                    initialIndex = i;
                }
            }
        });
    }
}