package com.sarabada.tradingbots.repository;

import com.sarabada.tradingbots.model.Interval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervalRepository extends JpaRepository<Interval, Long> { }