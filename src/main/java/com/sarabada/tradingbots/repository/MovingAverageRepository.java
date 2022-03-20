package com.sarabada.tradingbots.repository;

import com.sarabada.tradingbots.enums.MovingAverageOperation;
import com.sarabada.tradingbots.enums.PeriodReference;
import com.sarabada.tradingbots.model.CurrencyPeriod;
import com.sarabada.tradingbots.model.MovingAverage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovingAverageRepository extends JpaRepository<MovingAverage, Long> {
    @Query("select ma " +
            "from MovingAverage ma " +
            "inner join ma.currencyPeriod cp " +
            "where cp = :currencyPeriod " +
            "and ma.operation = :operation " +
            "and ma.reference = :reference " +
            "and ma.periods = :periods ")
    Optional<MovingAverage> findByCurrencyPeriodAndTypeAndReferenceAndPeriods(@Param("currencyPeriod") CurrencyPeriod currencyPeriod
            , @Param("operation") MovingAverageOperation operation, @Param("reference") PeriodReference reference, @Param("periods") Integer periods);

    @Query("select ma " +
            "from MovingAverage ma " +
            "inner join ma.currencyPeriod cp " +
            "inner join cp.currency c " +
            "where ma.periods = :periods " +
            "and ma.operation = :operation " +
            "and ma.reference = :reference " +
            "and c.id = :currencyId " +
            "order by cp.sequence desc ")
    List<MovingAverage> findTwoLastShortMovingAveragesBy(@Param("periods") Integer periods
            , @Param("operation") MovingAverageOperation operation, @Param("reference") PeriodReference reference
            , @Param("currencyId") Long currencyId, Pageable pageable);

    @Query("select ma " +
            "from MovingAverage ma " +
            "inner join ma.currencyPeriod cp " +
            "where ma.periods = :periods " +
            "and ma.operation = :operation " +
            "and ma.reference = :reference " +
            "and cp.id in (:currencyPeriodIds) " +
            "order by cp.sequence desc ")
    List<MovingAverage> findTwoLastLongMovingAverageBy(@Param("periods") Integer periods
            , @Param("operation") MovingAverageOperation operation, @Param("reference") PeriodReference reference
            , @Param("currencyPeriodIds") List<Long> currencyPeriodIds, Pageable pageable);
}