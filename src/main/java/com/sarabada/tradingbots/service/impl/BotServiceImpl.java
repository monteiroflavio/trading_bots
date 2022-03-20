package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.dto.BotDTO;
import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.enums.Entity;
import com.sarabada.tradingbots.enums.Operation;
import com.sarabada.tradingbots.exception.TradingBotsException;
import com.sarabada.tradingbots.mapper.BotMapper;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.model.BotCurrency;
import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.model.MovingAverageConfig;
import com.sarabada.tradingbots.repository.BotRepository;
import com.sarabada.tradingbots.service.BotCurrencyService;
import com.sarabada.tradingbots.service.BotService;
import com.sarabada.tradingbots.service.CurrencyService;
import com.sarabada.tradingbots.service.MovingAverageConfigService;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.ExceptionHandler;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BotServiceImpl implements BotService {

    private BotMapper mapper;
    private BotRepository repository;
    private CurrencyService currencyService;
    private BotCurrencyService botCurrencyService;
    private MovingAverageConfigService movingAverageConfigService;

    @Override
    public BotDTO create(BotDTO botDTO) {
        try {
            Bot bot = this.save(mapper.toModel(botDTO));
            List<MovingAverageConfig> movingAverageConfigs = movingAverageConfigService.saveAll(bot, botDTO.getMovingAverageConfigs());
            bot.setMovingAverageConfigs(movingAverageConfigs);
            return mapper.toDTO(bot);
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.SAVE, Entity.BOT);
        }
    }

    @Override
    public List<BotDTO> list() {
		try {
            List<BotDTO> botsDTOs = this.findAll();
            if (botsDTOs.isEmpty()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.BOT.name()));
            }
            return botsDTOs;
        } catch (TradingBotsException e) {
		  throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.LIST, Entity.BOT);
        }
    }

    @Override
    public BotDTO update(Long id, BotDTO botDTO) {
        try {
            Optional<Bot> optionalBot = this.findById(id);
            if (optionalBot.isEmpty()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.BOT.name()));
            }
            Bot bot = this.save(mapper.toUpdatableModel(optionalBot.get(), botDTO));
            return mapper.toDTO(bot);
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.UPDATE, Entity.BOT);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<Bot> optionalBot = this.findById(id);
            if (optionalBot.isEmpty()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.BOT.name()));
            }
            Bot bot = optionalBot.get();
            this.repository.delete(bot);
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.DELETE, Entity.BOT);
        }
    }

    @Override
    public BotDTO linkCurrencies(Long botId, List<CurrencyDTO> currencyDTOs) {
        try {
            Optional<Bot> optionalBot = this.findById(botId);
            if (optionalBot.isEmpty()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.BOT.name()));
            }
            List<Currency> currencies = currencyService.findAllByIds(getCurrenciesIds(currencyDTOs));
            if (currencies.size() < currencyDTOs.size()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.CURRENCY.name()));
            }
            Bot bot = optionalBot.get();
            botCurrencyService.saveAll(getBotCurrenciesToSave(bot, currencies));
            setBotCurrencies(bot);
            return this.mapper.toDTO(bot);
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.SAVE, Entity.BOT_CURRENCY);
        }
    }

    private List<BotCurrency> getBotCurrenciesToSave(Bot bot, List<Currency> currencies) {
        return currencies.stream()
                .map(currency -> BotCurrency.builder().bot(bot).currency(currency).build())
                .collect(Collectors.toList());
    }

    private List<Long> getCurrenciesIds (List<CurrencyDTO> currencyDTOs) {
        return currencyDTOs.stream().map(CurrencyDTO::getId).collect(Collectors.toList());
    }

    private void setBotCurrencies(Bot bot) {
        bot.setBotCurrencies(botCurrencyService.findAllByBotId(bot));
    }

    private void setBotsCurrencies(List<Bot> bots) {
        bots.forEach(this::setBotCurrencies);
    }

    @Override
    public Bot save(Bot bot) {
        return repository.save(bot);
    }

    @Override
    public Optional<Bot> findById(Long id) {
        return this.repository.findById(id);
    }

    private List<BotDTO> findAll() {
        List<Bot> bots = repository.findAll();
        setBotsCurrencies(bots);
        return mapper.toDTO(bots);
    }
}