package com.kata.bank.kataBank.entity;

import java.math.BigDecimal;

public class AccountHistory {
    private String idHistory;

    private String idAccount;

    private ActionsEnum typeOperation;

    private BigDecimal newBalance;

    private BigDecimal oldBalance;

    private String timeOperation;

    public void setIdHistory(String idHistory) {
        this.idHistory = idHistory;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public void setTypeOperation(ActionsEnum typeOperation) {
        this.typeOperation = typeOperation;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public void setOldBalance(BigDecimal oldBalance) {
        this.oldBalance = oldBalance;
    }

    public void setTimeOperation(String timeOperation) {
        this.timeOperation = timeOperation;
    }

    public String getIdHistory() {
        return idHistory;
    }

    public ActionsEnum getTypeOperation() {
        return typeOperation;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public BigDecimal getOldBalance() {
        return oldBalance;
    }

    public String getTimeOperation() {
        return timeOperation;
    }
}
