package com.sarabada.tradingbots.repository;

import com.sarabada.tradingbots.model.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends JpaRepository<Bot, Long> { }