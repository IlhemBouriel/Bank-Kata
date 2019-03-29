package com.kata.bank.kataBank.manager;

import com.kata.bank.kataBank.entity.Account;
import com.kata.bank.kataBank.entity.AccountHistory;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountManager {
    Account findAccountById(String id, List<Account> accountsList);
    Account makeOperationOnAccount(String accountId, BigDecimal amount, int operationId,List<Account> accountsList);
    Account depositOnAccount(String accountId, BigDecimal amount,List<Account> accountsList);
    Account withdrawalOnAccount(String accountId, BigDecimal amount,List<Account> accountsList);
    List<AccountHistory> historyAccount(String accountId);
}
