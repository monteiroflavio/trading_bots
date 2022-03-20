package com.sarabada.tradingbots.controller;

import com.sarabada.tradingbots.controller.validator.BotCurrencyValidator;
import com.sarabada.tradingbots.controller.validator.CurrencyValidator;
import com.sarabada.tradingbots.dto.BotCurrencyDTO;
import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.service.BotCurrencyService;
import com.sarabada.tradingbots.service.BotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/v1/bots", produces = "application/json")
public class BotCurrencyController {

    private BotService botService;
    private BotCurrencyService service;
    private BotCurrencyValidator validator;
    private CurrencyValidator currencyValidator;

    @PostMapping("/{botId}/currencies")
    public ResponseEntity create (@PathVariable("botId") Long botId, @RequestBody  List<CurrencyDTO> currencies) {
        currencyValidator.validateLinkToBot(currencies);
        return ResponseEntity.status(HttpStatus.CREATED).body(botService.linkCurrencies(botId, currencies));
    }

    @PutMapping("/{botId}/currencies/{botCurrencyId}")
    public ResponseEntity update (@PathVariable("botId") Long botId
            , @PathVariable("botCurrencyId") Long botCurrencyId, @RequestBody BotCurrencyDTO botCurrencyDTO) {
        validator.validateUpdate(botId,botCurrencyId, botCurrencyDTO);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(botId, botCurrencyId, botCurrencyDTO));
    }

    @DeleteMapping("/{botId}/currencies/{botCurrencyId}")
    public ResponseEntity delete (@PathVariable("botId") Long botId, @PathVariable("botCurrencyId") Long botCurrencyId) {
        validator.validateDelete(botId,botCurrencyId);
        service.delete(botId, botCurrencyId);
        return ResponseEntity.noContent().build();
    }
}