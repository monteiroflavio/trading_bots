package com.sarabada.tradingbots.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BotDTO {
    private Long id;
    private String name;
    private Integer interval;
    private Long orderQuantity;
    private List<MovingAverageConfigDTO> movingAverageConfigs;
    private List<BotCurrencyDTO> currencies;
}