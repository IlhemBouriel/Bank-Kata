package com.kata.bank.kataBank.operation;

import com.kata.bank.kataBank.entity.Account;
import com.kata.bank.kataBank.entity.AccountHistory;
import com.kata.bank.kataBank.entity.ActionsEnum;
import com.kata.bank.kataBank.utils.DateFormatter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AccountOperation implements IAccountOperation {

    @Override
    public Account addAmountOperation(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        return account;
    }

    @Override
    public void checkAmountOperation(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("exception.illegal.argument.amount");
        }
    }

    @Override
    public Account substractAmountOperation(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().subtract(amount));
        return account;
    }

    @Override
    public AccountHistory CreateHistoryOperation(Account account, BigDecimal oldBalance, int operationType ) {
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setIdHistory(UUID.randomUUID().toString().replaceAll("-", ""));
        accountHistory.setIdAccount(account.getIdAccount());
        accountHistory.setTypeOperation(operationType == 1 ? ActionsEnum.DEPOSIT : ActionsEnum.WITHDRAW);
        accountHistory.setNewBalance(account.getBalance());
        accountHistory.setOldBalance(oldBalance);
        accountHistory.setTimeOperation(DateFormatter.format(Calendar.getInstance()));
        return accountHistory;
    }

    @Override
    public Account findAccountById(List<Account> accountList, String idAccount) {
        return accountList.stream().filter(account -> idAccount.equals(account.getIdAccount()))
                .findAny()
                .orElse(null);
    }

    public List<AccountHistory> getAccountHistory(String accountId, List<AccountHistory> history) {
        return history.stream().filter(hist -> accountId.equals(hist.getIdAccount()))
                .collect(Collectors.toList());
    }
}
