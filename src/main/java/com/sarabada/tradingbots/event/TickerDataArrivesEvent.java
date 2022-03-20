package com.sarabada.tradingbots.event;

import com.sarabada.tradingbots.dto.TickerDataDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TickerDataArrivesEvent {
    TickerDataDTO tickerDataDTO;
}