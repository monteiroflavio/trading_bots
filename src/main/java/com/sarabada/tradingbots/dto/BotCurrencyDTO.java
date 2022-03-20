package com.sarabada.tradingbots.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sarabada.tradingbots.enums.BotCurrencyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BotCurrencyDTO {
    private Long id;
    private BotDTO bot;
    private CurrencyDTO currency;
    private BotCurrencyStatus status;
}