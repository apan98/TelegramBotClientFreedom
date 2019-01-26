package com.telegramBotClient.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "currency_history")
public class CurrencyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @NotNull private Currency currency;

    @NotNull private Double value;

    @NotNull private Date date;

}
