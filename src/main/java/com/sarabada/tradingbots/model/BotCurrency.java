package com.sarabada.tradingbots.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sarabada.tradingbots.enums.BotCurrencyStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bots_currencies")
public class BotCurrency {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Getter(onMethod = @__( @JsonIgnore))
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bot_id", referencedColumnName = "id")
    private Bot bot;

    @Getter(onMethod = @__( @JsonIgnore))
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @Column(name = "creation_date", insertable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", insertable = false, updatable = false)
    private Date updateDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", insertable = false)
    private BotCurrencyStatus status;
}