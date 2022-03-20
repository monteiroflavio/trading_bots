package com.sarabada.tradingbots.dto;

import com.sarabada.tradingbots.enums.MovingAverageOperation;
import com.sarabada.tradingbots.enums.MovingAverageType;
import com.sarabada.tradingbots.enums.PeriodReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovingAverageConfigDTO {
    private Long id;
    private Integer periods;
    private MovingAverageType type;
    private MovingAverageOperation operation;
    private PeriodReference reference;
}