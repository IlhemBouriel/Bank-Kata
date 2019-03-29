package com.kata.bank.kataBank.manager;

import com.kata.bank.kataBank.operation.AccountOperation;
import com.kata.bank.kataBank.entity.Account;
import com.kata.bank.kataBank.entity.AccountHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AccountManager implements IAccountManager{
    @Autowired
    private AccountOperation accountAction;

    public void setAccountAction(AccountOperation accountAction) {
        this.accountAction = accountAction;
    }

    private static List historyAccounts = new ArrayList<AccountHistory>();

    @Override
    public Account findAccountById(String id, List<Account> accountsList) {
        return this.accountAction.findAccountById(accountsList,id);
    }

    @Override
    public Account depositOnAccount(String accountId, BigDecimal amount,List<Account> accountsList) {
        this.accountAction.checkAmountOperation(amount);
        Account account = this.accountAction.findAccountById(accountsList, accountId);
        if (Objects.isNull(account))
            return null;
        BigDecimal oldAmount = account.getBalance();
        account = this.accountAction.addAmountOperation(account,amount);
        historyAccounts.add(this.accountAction.CreateHistoryOperation(account,oldAmount,1));
        return account;

    }

    @Override
    public Account withdrawalOnAccount(String accountId, BigDecimal amount,List<Account> accountsList) {
        this.accountAction.checkAmountOperation(amount);
        Account account = this.accountAction.findAccountById(accountsList, accountId);
        if (Objects.isNull(account))
            return null;
        this.accountAction.checkAmountOperation(account.getBalance().subtract(amount));
        BigDecimal oldAmount = account.getBalance();
        account = this.accountAction.substractAmountOperation(account,amount);
        historyAccounts.add(this.accountAction.CreateHistoryOperation(account,oldAmount,2));
        return account;
    }


    public List<AccountHistory> historyAccount(String accountId) {
        return this.accountAction.getAccountHistory(accountId, historyAccounts);
    }

    public  Account makeOperationOnAccount(String accountId, BigDecimal amount, int operationId,List<Account> accountsList) {
        if (operationId == 1)
            return depositOnAccount(accountId,amount, accountsList);
        if (operationId == 2 )
            return withdrawalOnAccount(accountId,amount, accountsList);
        else
            return null;
    }
}
