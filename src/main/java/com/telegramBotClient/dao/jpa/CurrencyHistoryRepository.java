package com.telegramBotClient.dao.jpa;

import com.telegramBotClient.domain.model.CurrencyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyHistoryRepository extends JpaRepository<CurrencyHistory, Long> {

}
