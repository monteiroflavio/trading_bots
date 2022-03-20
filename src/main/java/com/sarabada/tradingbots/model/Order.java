package com.sarabada.tradingbots.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sarabada.tradingbots.enums.OrderType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Getter(onMethod = @__( @JsonIgnore))
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bot_id", referencedColumnName = "id")
    private Bot bot;

    @Column(name = "value")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OrderType type;

    @Column(name = "creation_date", insertable = false, updatable = false)
    private Date creationDate;
}