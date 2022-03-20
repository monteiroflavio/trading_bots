package com.sarabada.tradingbots.repository;

import com.sarabada.tradingbots.model.Currency;
import com.sarabada.tradingbots.model.CurrencyPeriod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyPeriodRepository extends PagingAndSortingRepository<CurrencyPeriod, Long> {
    @Query("select cp " +
            "from CurrencyPeriod cp " +
            "inner join cp.currency c " +
            "where :currency = c " +
            "and :interval = cp.interval")
    Page<CurrencyPeriod> findLastPeriodOfCurrency(@Param("currency") Currency currency, @Param("interval") Integer interval, Pageable pageable);

    @Query("select cp " +
            "from CurrencyPeriod cp " +
            "inner join cp.currency c " +
            "where :currency = c " +
            "and cp.sequence > :sequence")
    List<CurrencyPeriod> findAllByCurrencyAfterSequence(@Param("currency") Currency currency, @Param("sequence") Integer sequence);
}