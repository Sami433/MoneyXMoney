package com.example.MoneyXMoney.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Account {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer accountId;
    private Double amount;
    private String iban;
    private String address;
    @OneToOne
    User user;

}

