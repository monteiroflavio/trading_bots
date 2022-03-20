package com.sarabada.tradingbots.event;

import com.sarabada.tradingbots.model.CurrencyEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyEntryIsInsertedEvent {
    private CurrencyEntry currencyEntry;
}