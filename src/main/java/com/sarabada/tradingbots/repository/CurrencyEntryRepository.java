package com.sarabada.tradingbots.repository;

import com.sarabada.tradingbots.model.CurrencyEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CurrencyEntryRepository extends JpaRepository<CurrencyEntry, Long> {
    @Query("select ce " +
            "from CurrencyEntry ce " +
            "inner join ce.currency c " +
            "where c.id = :currencyId " +
            "and ce.entryDatetime > coalesce(:initialDatetime, 0) " +
            "order by ce.entryDatetime asc ")
    List<CurrencyEntry> findAllEntriesOrderedAfter(@Param("currencyId") Long currencyId
            , @Param("initialDatetime") Date initialDatetime);
}