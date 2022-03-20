package com.sarabada.tradingbots.mapper.impl;

import com.sarabada.tradingbots.dto.OrderDTO;
import com.sarabada.tradingbots.mapper.OrderMapper;
import com.sarabada.tradingbots.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .type(order.getType())
                .value(order.getValue())
                .id(order.getId())
                .build();
    }

    @Override
    public List<OrderDTO> toDTO(List<Order> orders) {
        return orders.stream().map(this::toDTO).collect(Collectors.toList());
    }
}