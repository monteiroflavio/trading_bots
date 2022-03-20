package com.sarabada.tradingbots.event;

import com.sarabada.tradingbots.model.BotCurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotCurrencyStatusIsUpdatedEvent {
    private BotCurrency botCurrency;
}