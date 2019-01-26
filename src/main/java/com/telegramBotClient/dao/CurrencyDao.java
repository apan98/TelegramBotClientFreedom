package com.telegramBotClient.dao;

import com.telegramBotClient.domain.model.Currency;

public interface CurrencyDao {
    Currency getByCode(String code);
}
