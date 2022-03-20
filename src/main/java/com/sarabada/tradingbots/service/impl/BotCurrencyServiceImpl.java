package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.dto.BotCurrencyDTO;
import com.sarabada.tradingbots.enums.BotCurrencyStatus;
import com.sarabada.tradingbots.enums.Entity;
import com.sarabada.tradingbots.enums.Operation;
import com.sarabada.tradingbots.event.BotCurrencyStatusIsUpdatedEvent;
import com.sarabada.tradingbots.exception.TradingBotsException;
import com.sarabada.tradingbots.mapper.BotCurrencyMapper;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.model.BotCurrency;
import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.repository.BotCurrencyRepository;
import com.sarabada.tradingbots.service.BotCurrencyService;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.ExceptionHandler;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class BotCurrencyServiceImpl implements BotCurrencyService {

    private BotCurrencyMapper mapper;
    private BotCurrencyRepository repository;
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<BotCurrency> saveAll(List<BotCurrency> botCurrencies) {
        try {
            return this.repository.saveAll(botCurrencies);
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.SAVE, Entity.BOT_CURRENCY);
        }
    }

    @Override
    public BotCurrencyDTO update(Long botId, Long botCurrencyId, BotCurrencyDTO botCurrencyDTO) {
        try {
            BotCurrency botCurrency = this.findBotCurrencyAndValidate(botId, botCurrencyId);
            botCurrency.setStatus(botCurrencyDTO.getStatus());
            botCurrency = this.save(botCurrency);
            botCurrency.getBot().setBotCurrencies(null);
            publishStatusUpdatedEvent(botCurrency);
            return mapper.toDTO(botCurrency);
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.UPDATE, Entity.BOT_CURRENCY);
        }
    }

    private void publishStatusUpdatedEvent(BotCurrency botCurrency) {
        eventPublisher.publishEvent(BotCurrencyStatusIsUpdatedEvent.builder().botCurrency(botCurrency).build());
    }

    @Override
    public void delete(Long botId, Long botCurrencyId) {
        try {
            BotCurrency botCurrency = this.findBotCurrencyAndValidate(botId, botCurrencyId);
            repository.delete(botCurrency);
            botCurrency.setStatus(BotCurrencyStatus.DISABLED);
            publishStatusUpdatedEvent(botCurrency);
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.DELETE, Entity.BOT_CURRENCY);
        }
    }

    @Override
    public List<BotCurrency> findAllByBotId(Bot bot) {
        return this.repository.findAll(Example.of(BotCurrency.builder().bot(bot).build()));
    }

    private BotCurrency findBotCurrencyAndValidate(Long botId, Long botCurrencyId) {
        Optional<BotCurrency> optionalBotCurrency = this.repository.findById(botCurrencyId);
        if (optionalBotCurrency.isEmpty()) {
            throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.BOT_CURRENCY.name()));
        }
        BotCurrency botCurrency = optionalBotCurrency.get();
        if (!botCurrency.getBot().getId().equals(botId)) {
            throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.BOT_CURRENCY.name()));
        }
        return botCurrency;
    }

    @Override
    public List<BotCurrency> findAllByCurrency(Currency currency) {
        return repository.findAll(Example.of(BotCurrency.builder().currency(currency).status(BotCurrencyStatus.ENABLED).build()));
    }

    @Override
    public List<BotCurrency> findAllByCurrencyAndInterval(Currency currency, Integer interval) {
        return repository.findAllByCurrencyAndInterval(currency, interval);
    }

    private BotCurrency save(BotCurrency botCurrency) {
        return this.repository.save(botCurrency);
    }
}