package com.telegramBotClient.bot;

import com.telegramBotClient.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@AllArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private MessageService messageService;


    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        String help = "/currencies — список доступных валют\n" +
                "{currencyCode} — стоймость валюты к тенге\n" +
                "{currencyCode} {number} — currencyCode название валюты, а number количество валюты к тенге по текущему курсу\n" +
                "{currencyCode} history — история изменения валюты (Срезы по 10 минут)\n";
        Message message = update.getMessage();
        String answer = "";
        log.info("command: ".concat(message.getText()));
        String command = message.getText();
        if (message.getText().substring(0, 1).equals("/")) {
            if (command.equals("/help")) {
                answer = help;
            }
            if (command.equals("/currencies")) {
                answer = messageService.getCurrencies().toString();
            }
        } else {
            List<String> codeList = messageService.getCurrencies();
            String currencyCode = "";
            for (int i = 0; i < codeList.size(); i++) {
                if (command.toLowerCase().contains(codeList.get(i).toLowerCase())) {
                    currencyCode = codeList.get(i);
                }
            }
            if (!currencyCode.isEmpty()) {
                answer += "Выбрана валюта: ".concat(currencyCode).concat("\n");

                Pattern p = Pattern.compile("(\\d+\\.?\\d*)");
                Matcher matcher = p.matcher(command);
                List<Double> doubles = new ArrayList<>();
                if (matcher.find()) {
                    doubles.add(Double.parseDouble(matcher.group()));
                    answer += "количество: ".concat(doubles.get(0).toString()).concat("\n");
                }
                Double currentCurrency = messageService.getCurrentCurrency(currencyCode);
                answer += "Курс к тенге: ".concat(currentCurrency.toString().concat("\n"));
                if (doubles.size() > 0 && doubles.get(0) != null) {
                    Double summ = currentCurrency * doubles.get(0);
                    answer += "Всего: ".concat(summ.toString());
                }
                if (command.toLowerCase().contains("history".toLowerCase())) {
                    SendDocument sendDocument = messageService.getHistoryDocument(currencyCode);
                    sendDocument.setChatId(message.getChatId());
                    execute(sendDocument);
                }
            }
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        if (!answer.isEmpty()) {
            sendMessage.setText(answer);
            execute(sendMessage);
        } else {
            sendMessage.setText(help);
            execute(sendMessage);
        }
    }

    public String getBotUsername() {
        return "currecny_freedom1_bot";
    }

    public String getBotToken() {
        return "781952687:AAHyLLQYiZzyDgxyoR6P_0H9DtrYqxPmzcU";
    }

}

