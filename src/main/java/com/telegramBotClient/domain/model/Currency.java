package com.telegramBotClient.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull private String code;
    @NotNull private String description;

    public enum Code {
        EUR,
        RUR,
        USD
    }
}
