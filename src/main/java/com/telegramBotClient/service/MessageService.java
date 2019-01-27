package com.telegramBotClient.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface MessageService {

    List<String> getCurrencies();

    Double getCurrentCurrency(String currency);

    SendDocument getHistoryDocument(String currency);


}
