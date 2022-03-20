package com.sarabada.tradingbots.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelSubscriptionDTO {
    private String command;
    private Integer channel;
}