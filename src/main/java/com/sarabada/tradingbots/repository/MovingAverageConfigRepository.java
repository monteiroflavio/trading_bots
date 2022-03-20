package com.sarabada.tradingbots.repository;

import com.sarabada.tradingbots.model.MovingAverageConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovingAverageConfigRepository extends JpaRepository<MovingAverageConfig, Long> { }