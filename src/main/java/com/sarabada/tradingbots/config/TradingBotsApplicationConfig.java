package com.sarabada.tradingbots.config;

import com.sarabada.tradingbots.client.OrderEntryClientEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.websocket.DeploymentException;
import java.io.IOException;

@Configuration
public class TradingBotsApplicationConfig {
    @Bean
    public OrderEntryClientEndpoint getOrderEntryClientEndpoint() throws IOException, DeploymentException {
        return new OrderEntryClientEndpoint();
    }
}