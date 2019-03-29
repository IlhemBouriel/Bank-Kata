package com.kata.bank.kataBank.entity;

import java.math.BigDecimal;

public class Account {
    private String idAccount;

    private String idUser;

    private BigDecimal balance;

    public Account(String idAccount, String idUser, BigDecimal balance) {
        this.idAccount = idAccount;
        this.idUser = idUser;
        this.balance = balance;
    }

    public Account(String idAccount, String idUser) {
        this.idAccount = idAccount;
        this.idUser = idUser;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
