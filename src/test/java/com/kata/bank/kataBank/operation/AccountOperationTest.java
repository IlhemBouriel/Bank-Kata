package com.kata.bank.kataBank.operation;

import com.kata.bank.kataBank.entity.Account;
import com.kata.bank.kataBank.entity.AccountHistory;
import com.kata.bank.kataBank.entity.ActionsEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountOperationTest {

    private Account account;
    @Mock
    private AccountOperation accountOperation;

    @Before
    public void setUp() {
        account = new Account("idAccount", "idHolder");
        accountOperation = new AccountOperation();
    }

    @Test
    public void addAmountOperationTest_0() {
        BigDecimal balance = BigDecimal.ZERO;
        account.setBalance(balance);

        BigDecimal amount = BigDecimal.valueOf(32);
        accountOperation.addAmountOperation(account, amount);

        Assert.assertEquals(account.getBalance(), balance.add(amount));
    }

    @Test
    public void addAmountOperationTest() {
        BigDecimal balance = BigDecimal.valueOf(90.32);
        account.setBalance(balance);

        BigDecimal amount = BigDecimal.valueOf(32);
        accountOperation.addAmountOperation(account, amount);

        Assert.assertEquals(account.getBalance(), balance.add(amount));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAmountOperation_null() {
        accountOperation.checkAmountOperation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAmountOperation_lt0() {
        accountOperation.checkAmountOperation(BigDecimal.valueOf(-1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAmountOperation_0() {
        accountOperation.checkAmountOperation(BigDecimal.ZERO);
    }

    @Test
    public void checkAmountOperation_gt0() {
        accountOperation.checkAmountOperation(BigDecimal.TEN);
    }

    @Test
    public void substractAmountOperationTest() {
        BigDecimal balance = BigDecimal.valueOf(90.32);
        account.setBalance(balance);

        BigDecimal amount = BigDecimal.valueOf(32);

        accountOperation.substractAmountOperation(account, amount);

        Assert.assertEquals(account.getBalance(), balance.subtract(amount));
    }

    @Test
    public void CreateHistoryOperationTest_Deposit() {
        BigDecimal balance = BigDecimal.ZERO;
        account.setBalance(balance);
        AccountHistory accountHistory = accountOperation.CreateHistoryOperation(account, BigDecimal.TEN, 1);
        Assert.assertEquals(BigDecimal.ZERO, accountHistory.getNewBalance());
        Assert.assertEquals(BigDecimal.TEN, accountHistory.getOldBalance());
        Assert.assertEquals(account.getIdAccount(), accountHistory.getIdAccount());
        Assert.assertEquals(ActionsEnum.DEPOSIT, accountHistory.getTypeOperation());
    }

    @Test
    public void CreateHistoryOperationTest_Withdraw() {
        BigDecimal balance = BigDecimal.ZERO;
        account.setBalance(balance);
        AccountHistory accountHistory = accountOperation.CreateHistoryOperation(account, BigDecimal.TEN, 2);
        Assert.assertEquals(BigDecimal.ZERO, accountHistory.getNewBalance());
        Assert.assertEquals(BigDecimal.TEN, accountHistory.getOldBalance());
        Assert.assertEquals(account.getIdAccount(), accountHistory.getIdAccount());
        Assert.assertEquals(ActionsEnum.WITHDRAW, accountHistory.getTypeOperation());
    }

    @Test
    public void findAccountByIdTest_Null() {
        Account account = accountOperation.findAccountById(new ArrayList<>(), "abc");
        Assert.assertNull(account);
    }

    @Test
    public void findAccountByIdTest() {
        Account account = accountOperation.findAccountById(Arrays.asList(new Account("abc", "user")), "abc");
        Assert.assertEquals(account.getIdAccount(), "abc");
    }

    @Test
    public void getAccountHistoryTest_Null() {
        List<AccountHistory> accountHistories = accountOperation.getAccountHistory("abc", new ArrayList<>());
        Assert.assertEquals(accountHistories,new ArrayList<>());
    }
}
