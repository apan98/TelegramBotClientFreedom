package com.telegramBotClient.service.impl;

import com.telegramBotClient.dao.CurrencyHistoryDao;
import com.telegramBotClient.domain.model.Currency;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

@Service
@RequiredArgsConstructor
public class ScheduledWriteCurrencyHistory {

    private String CURRENT_CURRENCY_URL = "http://www.nationalbank.kz/rss/rates.xml";
    private final CurrencyHistoryDao currencyHistoryDao;



    @Scheduled(fixedRate = 1000)
    @SneakyThrows
    public void reportCurrentTime() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(CURRENT_CURRENCY_URL)
                .build();
        String response = okHttpClient.newCall(request).execute().body().string();
        InputSource source = new InputSource(new StringReader(response));
        XPath xPath = XPathFactory.newInstance().newXPath();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc =  builder.parse(source);
        for (Currency.Code code : Currency.Code.values()) {
            currencyHistoryDao.writeHistory(code.name(), (Double) xPath.evaluate("/rss/channel/item[title='" + code.name()
                    + "']/description/text()", doc, XPathConstants.NUMBER));
        }
    }

}