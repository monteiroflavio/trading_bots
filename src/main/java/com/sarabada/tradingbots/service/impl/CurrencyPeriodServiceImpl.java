package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.enums.Entity;
import com.sarabada.tradingbots.enums.Operation;
import com.sarabada.tradingbots.event.CurrencyPeriodIsInsertedEvent;
import com.sarabada.tradingbots.mapper.CurrencyPeriodMapper;
import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.model.CurrencyEntry;
import com.sarabada.tradingbots.model.CurrencyPeriod;
import com.sarabada.tradingbots.repository.CurrencyPeriodRepository;
import com.sarabada.tradingbots.service.CurrencyPeriodService;
import com.sarabada.tradingbots.utils.ExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyPeriodServiceImpl implements CurrencyPeriodService {

    CurrencyPeriodMapper mapper;
    CurrencyPeriodRepository repository;
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Optional<CurrencyPeriod> findLastPeriodOfCurrencyToInterval(Currency currency, Integer interval) {
        Page<CurrencyPeriod> page = repository
                .findLastPeriodOfCurrency(currency, interval, PageRequest.of(0,1, Sort.by("sequence").descending()));
        return page.get().findFirst();
    }

    @Override
    public void save(List<CurrencyEntry> currencyEntries, Integer interval, Integer sequence) {
        try {
            CurrencyPeriod currencyPeriod = repository.save(mapper.toModel(currencyEntries, interval, sequence));
            applicationEventPublisher.publishEvent(CurrencyPeriodIsInsertedEvent.builder()
                    .currencyPeriod(currencyPeriod)
                    .build());
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.SAVE, Entity.CURRENCY_PERIOD);
        }
    }

    @Override
    public List<CurrencyPeriod> findAllByCurrencyAfterSequence(Currency currency, Integer sequence) {
        return repository.findAllByCurrencyAfterSequence(currency, sequence);
    }
}