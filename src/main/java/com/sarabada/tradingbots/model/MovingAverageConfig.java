package com.sarabada.tradingbots.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sarabada.tradingbots.enums.MovingAverageOperation;
import com.sarabada.tradingbots.enums.MovingAverageType;
import com.sarabada.tradingbots.enums.PeriodReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "moving_average_configs")
public class MovingAverageConfig {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "periods")
    private Integer periods;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MovingAverageType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation")
    private MovingAverageOperation operation;

    @Enumerated(EnumType.STRING)
    @Column(name = "reference")
    private PeriodReference reference;

    @Getter(onMethod = @__( @JsonIgnore))
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bot_id", referencedColumnName = "id")
    private Bot bot;

    @Column(name = "creation_date", insertable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", insertable = false, updatable = false)
    private Date updateDate;
}