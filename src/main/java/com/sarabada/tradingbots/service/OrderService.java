package com.sarabada.tradingbots.service;

import com.sarabada.tradingbots.dto.OrderDTO;
import com.sarabada.tradingbots.enums.OrderType;
import com.sarabada.tradingbots.model.Bot;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void save(Bot bot, BigDecimal value, OrderType type);
    List<OrderDTO> listByBotId(Long botId);
}