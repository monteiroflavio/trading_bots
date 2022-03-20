package com.sarabada.tradingbots.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TickerDataDTO {
    private Long currencyPairId;
    private BigDecimal lastTradePrice;
    private BigDecimal lowestAsk;
    private BigDecimal highestBid;
    private BigDecimal twentyFourHourPercentageChange;
    private BigDecimal twentyFourHourBaseCurrencyVolume;
    private BigDecimal twentyFourHourQuoteCurrencyVolume;
    private Boolean isFrozen;
    private BigDecimal twentyFourHourHighestTradePrice;
    private BigDecimal twentyFourHourLowestTradePrice;
    private Date messageTime;
}