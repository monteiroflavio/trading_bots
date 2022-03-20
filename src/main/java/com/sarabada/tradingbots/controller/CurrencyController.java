package com.sarabada.tradingbots.controller;

import com.sarabada.tradingbots.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/v1/currencies", produces = "application/json")
public class CurrencyController {
    private CurrencyService service;
    @GetMapping
    public ResponseEntity list() {
        return ResponseEntity.status(HttpStatus.OK).body(service.list());
    }
}