package com.sarabada.tradingbots.client;

import com.google.gson.Gson;
import com.sarabada.tradingbots.dto.ChannelSubscriptionDTO;
import com.sarabada.tradingbots.enums.Channel;
import com.sarabada.tradingbots.event.TickerDataArrivesEvent;
import com.sarabada.tradingbots.mapper.TickerDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class OrderEntryClientEndpoint {

    private Session session;
    private final String uri = "wss://api2.poloniex.com";

    @Autowired
    private TickerDataMapper tickerDataMapper;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public OrderEntryClientEndpoint() throws IOException, DeploymentException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, URI.create(uri));
    }

    @OnOpen
    public void processOpen(Session session) throws IOException {
        this.session = session;
        ChannelSubscriptionDTO subscription = ChannelSubscriptionDTO.builder()
                    .command("subscribe")
                    .channel(Channel.TICKER_DATA.getChannel())
                .build();
        this.sendMessage(new Gson().toJson(subscription));
    }

    @OnMessage
    public void processMessage(String message) {
        applicationEventPublisher.publishEvent(TickerDataArrivesEvent.builder()
                .tickerDataDTO(tickerDataMapper.toDTO(message))
                .build());
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}