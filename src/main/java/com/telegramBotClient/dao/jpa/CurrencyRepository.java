package com.telegramBotClient.dao.jpa;

import com.telegramBotClient.domain.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency getByCode(String code);

}
