package com.sarabada.tradingbots.mapper.impl;

import com.sarabada.tradingbots.dto.TickerDataDTO;
import com.sarabada.tradingbots.mapper.TickerDataMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TickerDataMapperImpl implements TickerDataMapper {

    @Override
    public TickerDataDTO toDTO(String message) {
        int i = message.lastIndexOf('[') + 1;
        int j = message.indexOf(']');
        TickerDataDTO tickerDataDTO = null;
        List<String> infos = Arrays.stream(message.substring(i, j).split(","))
                .map(s -> s.replace("\"", ""))
                .collect(Collectors.toList());
        if (infos.size() > 2) {
            tickerDataDTO = TickerDataDTO.builder()
                    .currencyPairId(Long.valueOf(infos.get(0)))
                    .lastTradePrice(new BigDecimal(infos.get(1)))
                    .lowestAsk(new BigDecimal(infos.get(2)))
                    .highestBid(new BigDecimal(infos.get(3)))
                    .twentyFourHourPercentageChange(new BigDecimal(infos.get(4)))
                    .twentyFourHourBaseCurrencyVolume(new BigDecimal(infos.get(5)))
                    .twentyFourHourQuoteCurrencyVolume(new BigDecimal(infos.get(6)))
                    .isFrozen(Boolean.valueOf(infos.get(7)))
                    .twentyFourHourHighestTradePrice(new BigDecimal(infos.get(8)))
                    .twentyFourHourLowestTradePrice(new BigDecimal(infos.get(9)))
                    .messageTime(new Date())
                    .build();
        }
        return  tickerDataDTO;
    }
}