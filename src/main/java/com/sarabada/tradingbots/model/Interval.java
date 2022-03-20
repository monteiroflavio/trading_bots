package com.sarabada.tradingbots.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "intervals")
public class Interval {
    @Id
    @Column(name = "interval")
    private Integer interval;

    @Column(name = "creation_date", insertable = false, updatable = false)
    private Date creationDate;
}