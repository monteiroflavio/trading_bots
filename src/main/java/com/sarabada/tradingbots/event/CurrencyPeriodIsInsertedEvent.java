package com.sarabada.tradingbots.event;

import com.sarabada.tradingbots.model.CurrencyPeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyPeriodIsInsertedEvent {
    CurrencyPeriod currencyPeriod;
}