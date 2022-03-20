package com.sarabada.tradingbots.repository;

import com.sarabada.tradingbots.model.BotCurrency;
import com.sarabada.tradingbots.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotCurrencyRepository extends JpaRepository<BotCurrency, Long> {
    @Query("select bc " +
            "from BotCurrency bc " +
            "inner join bc.bot b " +
            "inner join bc. currency c " +
            "where c = :currency " +
            "and b.interval = :interval ")
    List<BotCurrency> findAllByCurrencyAndInterval(@Param("currency") Currency currency, @Param("interval") Integer interval);
}