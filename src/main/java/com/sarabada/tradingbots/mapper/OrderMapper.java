package com.sarabada.tradingbots.mapper;

import com.sarabada.tradingbots.dto.OrderDTO;
import com.sarabada.tradingbots.model.Order;

import java.util.List;

public interface OrderMapper {
    OrderDTO toDTO(Order order);
    List<OrderDTO> toDTO(List<Order> orders);
}