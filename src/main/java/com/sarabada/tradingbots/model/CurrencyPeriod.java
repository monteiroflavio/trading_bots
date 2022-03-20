package com.sarabada.tradingbots.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currencies_periods")
public class CurrencyPeriod {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sequence")
    private Integer sequence;

    @Getter(onMethod = @__(@JsonIgnore))
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @Getter(onMethod = @__(@JsonIgnore))
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_entry_start_id", referencedColumnName = "id")
    private CurrencyEntry currencyEntryStart;

    @Getter(onMethod = @__(@JsonIgnore))
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_entry_end_id", referencedColumnName = "id")
    private CurrencyEntry currencyEntryEnd;

    @Column(name = "open_value")
    private BigDecimal openValue;

    @Column(name = "close_value")
    private BigDecimal closeValue;

    @Column(name = "high_value")
    private BigDecimal highValue;

    @Column(name = "low_value")
    private BigDecimal lowValue;

    @Column(name = "interval")
    private Integer interval;

    @Column(name = "creation_date", insertable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", insertable = false, updatable = false)
    private Date updateDate;
}