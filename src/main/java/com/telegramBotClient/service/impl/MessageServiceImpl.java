package com.telegramBotClient.service.impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telegramBotClient.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    public static String CURRENT_CURRENCY_URL = "http://localhost:8080/api/v1/currencies";
//    private final AbsSender bot;

    @SneakyThrows
    public BotApiMethod inputMessage(Message message) {

//        System.out.println(message.getText());
//                String response = getCurrentCurrency(message.getText());
//        if (response.length() <= 4095) {
//            SendMessage messageOut = new SendMessage() // Create a SendMessage object with mandatory fields
//                    .setChatId(message.getChatId());
//            messageOut.setText(response);
//            return messageOut; // Call method to send the message
//        } else {
//            InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
//            SendDocument sendDocumentRequest = new SendDocument().setChatId(message.getChatId())
//                    .setDocument("history", stream);
//            BotApiMethod(sendDocumentRequest);
//            return new;
        return null;
    }

    @Override
    @SneakyThrows
    public List<String> getCurrencies() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CURRENT_CURRENCY_URL)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode valuesNode = mapper.readTree(okHttpClient.newCall(request).execute().body().string());
        List<String> list = new ArrayList<>();
        for (JsonNode node : valuesNode) {
            list.add(node.asText());
        }
        return list;
    }

    @SneakyThrows
    public Double getCurrentCurrency(String currency) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CURRENT_CURRENCY_URL.concat("/") + currency.toUpperCase())
                .build();
        String response = okHttpClient.newCall(request).execute().body().string();
        return Double.valueOf(response);
    }

    @Override
    @SneakyThrows
    public SendDocument getHistoryDocument(String currency) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CURRENT_CURRENCY_URL.concat("/").concat(currency).concat("/history"))
                .build();

        String response = okHttpClient.newCall(request).execute().body().string();
        InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
        SendDocument sendDocument = new SendDocument();
        sendDocument.setDocument("history.txt", stream);
        return sendDocument;
    }


}
