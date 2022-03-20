package com.sarabada.tradingbots.controller;

import com.sarabada.tradingbots.controller.validator.OrderValidator;
import com.sarabada.tradingbots.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/v1/bots/{bot_id}/orders", produces = "application/json")
public class OrderController {
    private OrderService service;
    private OrderValidator validator;
    @GetMapping
    public ResponseEntity listByBotId(@PathVariable("bot_id") Long botId) {
        validator.validateListByBotId(botId);
        return ResponseEntity.status(HttpStatus.OK).body(service.listByBotId(botId));
    }
}