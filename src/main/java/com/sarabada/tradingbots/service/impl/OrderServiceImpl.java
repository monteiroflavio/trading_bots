package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.dto.OrderDTO;
import com.sarabada.tradingbots.enums.Entity;
import com.sarabada.tradingbots.enums.Operation;
import com.sarabada.tradingbots.enums.OrderType;
import com.sarabada.tradingbots.exception.TradingBotsException;
import com.sarabada.tradingbots.mapper.OrderMapper;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.model.Order;
import com.sarabada.tradingbots.repository.OrderRepository;
import com.sarabada.tradingbots.service.BotService;
import com.sarabada.tradingbots.service.OrderService;
import com.sarabada.tradingbots.utils.ErrorDTOFactory;
import com.sarabada.tradingbots.utils.ExceptionHandler;
import com.sarabada.tradingbots.utils.TradingBotsExceptionFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private BotService botService;
    private OrderRepository repository;
    private OrderMapper mapper;

    @Override
    public void save(Bot bot, BigDecimal value, OrderType type) {
        repository.save(Order.builder().bot(bot).value(value).type(type).build());
    }

    @Override
    public List<OrderDTO> listByBotId(Long botId) {
        try {
            Optional<Bot> optionalBot = botService.findById(botId);
            if (optionalBot.isEmpty()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.BOT.name()));
            }
            List<Order> orders = repository.findAll(Example.of(Order.builder().bot(optionalBot.get()).build()));
            if (orders.isEmpty()) {
                throw TradingBotsExceptionFactory.getNotFound(ErrorDTOFactory.getNotFound(Entity.ORDER.name()));
            }
            return mapper.toDTO(orders);
        } catch (TradingBotsException e) {
            throw e;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e.getMessage(), Operation.LIST, Entity.ORDER);
        }
    }
}