package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.enums.Entity;
import com.sarabada.tradingbots.enums.Operation;
import com.sarabada.tradingbots.exception.TradingBotsException;
import com.sarabada.tradingbots.mapper.CurrencyMapper;
import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.repository.CurrencyRepository;
import com.sarabada.tradingbots.service.CurrencyService;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.ExceptionHandler;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyMapper mapper;
    private CurrencyRepository repository;

    @Override
    public List<CurrencyDTO> list() {
        try {
            List<CurrencyDTO> currencyDTOs = this.findAll();
            if (currencyDTOs.isEmpty()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.CURRENCY.name()));
            }
            return currencyDTOs;
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.LIST, Entity.CURRENCY);
        }
    }

    @Override
    public Optional<Currency> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Currency> findAllByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    private List<CurrencyDTO> findAll() {
        List<Currency> currencies = repository.findAll();
        return this.mapper.toDTO(currencies);
    }
}