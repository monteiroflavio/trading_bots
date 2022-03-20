package com.sarabada.tradingbots.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sarabada.tradingbots.enums.MovingAverageOperation;
import com.sarabada.tradingbots.enums.PeriodReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "moving_averages")
public class MovingAverage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Getter(onMethod = @__( @JsonIgnore))
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_period_id", referencedColumnName = "id")
    private CurrencyPeriod currencyPeriod;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "periods")
    private Integer periods;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation")
    private MovingAverageOperation operation;

    @Enumerated(EnumType.STRING)
    @Column(name = "reference")
    private PeriodReference reference;
}