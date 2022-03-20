package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.dto.TickerDataDTO;
import com.sarabada.tradingbots.enums.Entity;
import com.sarabada.tradingbots.enums.Operation;
import com.sarabada.tradingbots.event.CurrencyEntryIsInsertedEvent;
import com.sarabada.tradingbots.exception.TradingBotsException;
import com.sarabada.tradingbots.mapper.CurrencyEntryMapper;
import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.model.CurrencyEntry;
import com.sarabada.tradingbots.repository.CurrencyEntryRepository;
import com.sarabada.tradingbots.service.CurrencyEntryService;
import com.sarabada.tradingbots.service.CurrencyService;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.ExceptionHandler;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyEntryServiceImpl implements CurrencyEntryService {

    private CurrencyEntryMapper mapper;
    private CurrencyService currencyService;
    private CurrencyEntryRepository repository;
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public CurrencyEntry save(TickerDataDTO tickerDataDTO) {
        try {
            Optional<Currency> optionalCurrency = currencyService.findById(tickerDataDTO.getCurrencyPairId());
            if (optionalCurrency.isEmpty()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.CURRENCY.name()));
            }
            CurrencyEntry currencyEntry = mapper.toModel(tickerDataDTO, optionalCurrency.get());
            currencyEntry = repository.save(currencyEntry);
            applicationEventPublisher.publishEvent(CurrencyEntryIsInsertedEvent.builder().currencyEntry(currencyEntry).build());
            return currencyEntry;
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.SAVE, Entity.CURRENCY_ENTRY);
        }
    }

    @Override
    public List<CurrencyEntry> findAllEntriesOrderedAfter(CurrencyEntry currencyEntry) {
        return repository.findAllEntriesOrderedAfter(currencyEntry.getCurrency().getId()
                , currencyEntry.getEntryDatetime());
    }
}