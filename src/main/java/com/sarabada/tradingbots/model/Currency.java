package com.sarabada.tradingbots.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currencies")
public class Currency {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "currency")
    private String currency;
}