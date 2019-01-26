package com.telegramBotClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TelegramBotClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelegramBotClientApplication.class, args);
    }
}
