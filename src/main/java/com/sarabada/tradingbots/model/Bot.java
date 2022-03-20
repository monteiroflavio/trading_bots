package com.sarabada.tradingbots.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bots")
public class Bot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "interval")
    private Integer interval;

    @Column(name = "order_quantity")
    private Long orderQuantity;

    @Column(name = "creation_date", insertable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", insertable = false, updatable = false)
    private Date updateDate;

    @OneToMany(mappedBy = "bot", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @Fetch(FetchMode.SUBSELECT)
    private List<BotCurrency> botCurrencies;

    @OneToMany(mappedBy = "bot", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<MovingAverageConfig> movingAverageConfigs;
}