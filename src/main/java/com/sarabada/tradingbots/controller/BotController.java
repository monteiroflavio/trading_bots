package com.sarabada.tradingbots.controller;

import com.sarabada.tradingbots.controller.validator.BotValidator;
import com.sarabada.tradingbots.dto.BotDTO;
import com.sarabada.tradingbots.service.BotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/v1/bots", produces = "application/json")
public class BotController {
    private BotService service;
    private BotValidator validator;
    @PostMapping
    public ResponseEntity create(@RequestBody BotDTO botDTO) {
        validator.validateCreate(botDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(botDTO));
    }
    @GetMapping
    public ResponseEntity list() {
        return ResponseEntity.status(HttpStatus.OK).body(service.list());
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody BotDTO botDTO) {
        validator.validateUpdate(id);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, botDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        validator.validateDelete(id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}