package com.telegramBotClient.dao.mapper;

import com.telegramBotClient.domain.model.CurrencyHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CurrencyHistoryMapper {

    List<CurrencyHistory> getAllByCode(@Param("currencyCode") String currencyCode);

    CurrencyHistory getLastByCode(@Param("currencyCode") String currencyCode);

}
