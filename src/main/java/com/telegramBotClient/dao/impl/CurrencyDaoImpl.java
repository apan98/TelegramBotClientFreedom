package com.telegramBotClient.dao.impl;

import com.telegramBotClient.dao.CurrencyDao;
import com.telegramBotClient.dao.jpa.CurrencyRepository;
import com.telegramBotClient.domain.model.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyDaoImpl implements CurrencyDao {
    private final CurrencyRepository currencyRepository;

    @Override
    public Currency getByCode(String code) {
        return currencyRepository.getByCode(code);
    }
}
