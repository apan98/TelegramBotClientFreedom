package com.telegramBotClient.dao.impl;

import com.telegramBotClient.dao.CurrencyDao;
import com.telegramBotClient.dao.CurrencyHistoryDao;
import com.telegramBotClient.dao.jpa.CurrencyHistoryRepository;
import com.telegramBotClient.dao.mapper.CurrencyHistoryMapper;
import com.telegramBotClient.domain.model.CurrencyHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CurrencyHistoryDaoImpl implements CurrencyHistoryDao {

    private CurrencyDao currencyDao;
    private CurrencyHistoryRepository currencyHistoryRepository;
    private CurrencyHistoryMapper currencyHistoryMapper;

    @Override
    public void writeHistory(String currencyCode, Double value) {
        CurrencyHistory currencyHistory = new CurrencyHistory();
        currencyHistory.setCurrency(currencyDao.getByCode(currencyCode));
        currencyHistory.setDate(new Date());
        currencyHistory.setValue(value);
        currencyHistoryRepository.save(currencyHistory);
    }

    @Override
    public List<CurrencyHistory> getAllByCode(String currencyCode) {
        return currencyHistoryMapper.getAllByCode(currencyCode);
    }

    @Override
    public CurrencyHistory getLastByCode(String currencyCode) {
        return currencyHistoryMapper.getLastByCode(currencyCode);
    }
}
