
package com.sarabada.tradingbots.service.impl;

import com.sarabada.tradingbots.dto.CurrencyDTO;
import com.sarabada.tradingbots.model.Bot;
import com.sarabada.tradingbots.service.BotsPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BotsPoolServiceImpl implements BotsPoolService {

//    private List<RunnableBotServiceImpl> pool;

    @Override
    public void add(Bot bot) {
//        if (pool == null || pool.isEmpty()) {
//            pool = new ArrayList<>();
//        }
//        if (poolDoesNotContain(bot.getId())) {
//            pool.add(RunnableBotServiceImpl.builder()
//                    .id(bot.getId())
//                    .period(MovingAverageConfigDTO.builder()
//                            .lng(bot.getLongPeriods())
//                            .shrt(bot.getShortPeriods())
//                            .type(bot.getMovingAverageOperation())
//                            .build())
//                    .build());
//        } else {
//            log.warn(String.format("Bot #%s already in the pool", bot.getId()));
//        }
    }

    private boolean poolDoesNotContain(Long id) {
        return !poolContains(id);
    }

    private boolean poolContains(Long id) {
//        return pool.stream().map(RunnableBotServiceImpl::getId).anyMatch(rid -> rid.equals(id));
        return false;
    }

//    private RunnableBotServiceImpl poolBotOf(Long id) {
//        return pool.stream().filter(rb -> rb.getId().equals(id)).findFirst().orElseThrow();
//    }

    @Override
    public void remove(Long id) {
//        if (pool != null && !pool.isEmpty()) {
//            if (poolContains(id)) {
//                pool.remove(poolBotOf(id));
//            } else {
//                log.warn(String.format("Bot #%s was not in the pool", id));
//            }
//        }
    }

    @Override
    public void run(CurrencyDTO currency) {
//        pool.stream().filter(rb -> rb.getCurrencies().contains(currency)).forEach(RunnableBotServiceImpl::verifyOrderOpportunity);
    }
}
