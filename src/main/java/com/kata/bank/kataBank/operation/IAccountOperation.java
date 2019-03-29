package com.kata.bank.kataBank.operation;

import com.kata.bank.kataBank.entity.Account;
import com.kata.bank.kataBank.entity.AccountHistory;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountOperation {
    Account addAmountOperation(Account account, BigDecimal amount);
    void checkAmountOperation(BigDecimal amount);
    Account substractAmountOperation(Account account, BigDecimal amount);
    AccountHistory CreateHistoryOperation(Account account, BigDecimal oldBalance, int operationType );
    Account findAccountById(List<Account> account, String idAccount);
    List<AccountHistory> getAccountHistory(String accountId, List<AccountHistory> history);
}
