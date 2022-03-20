package com.sarabada.tradingbots.event.listener;

import com.sarabada.tradingbots.enums.BotCurrencyStatus;
import com.sarabada.tradingbots.event.BotCurrencyStatusIsUpdatedEvent;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.service.BotService;
import com.sarabada.tradingbots.service.BotsPoolService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class BotCurrencyStatusIsUpdatedListener {

    private BotService botService;
    private BotsPoolService botsPoolService;

    @Async
    @EventListener
    public void on (BotCurrencyStatusIsUpdatedEvent event) {
        Optional<Bot> optionalBot = botService.findById(event.getBotCurrency().getBot().getId());
        optionalBot.ifPresent(bot -> {
            if (event.getBotCurrency().getStatus().equals(BotCurrencyStatus.ENABLED)) {
                botsPoolService.add(bot);
            } else {
                botsPoolService.remove(bot.getId());
            }
        });
    }
}