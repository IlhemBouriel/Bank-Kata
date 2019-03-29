package com.kata.bank.kataBank.manager;

import com.kata.bank.kataBank.entity.Account;
import com.kata.bank.kataBank.operation.AccountOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountManagerTest {

    @Mock
    private AccountOperation accountAction;
    private AccountManager accountManager;
    private List<Account> accountsList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountManager = new AccountManager();
        accountManager.setAccountAction(accountAction);
        accountsList = new ArrayList<>();
        accountsList.add(new Account("123","user", BigDecimal.TEN));
    }

    @Test
    public void findAccountByIdTest() {
        Account account= new Account("123","user", BigDecimal.TEN) ;
        Mockito.when(accountAction.findAccountById(accountsList,"123")).thenReturn(account);
        Account actualAccount = accountManager.findAccountById("123",accountsList);
        Assert.assertEquals(account,actualAccount);

    }

    @Test
    public void depositOnAccountTest() {
        Account account= new Account("123","user", BigDecimal.ZERO) ;
        Account accountResult = new Account("123","user",BigDecimal.TEN);
        Mockito.when(accountAction.addAmountOperation(account,BigDecimal.TEN)).thenReturn(accountResult);
        Mockito.when(accountAction.findAccountById(accountsList,"123")).thenReturn(account);
        Account actualAccount = accountManager.depositOnAccount("123",BigDecimal.TEN,accountsList);
        Assert.assertEquals(actualAccount,accountResult);
    }

    @Test
    public void depositOnAccountTest_Null() {
        Account actualAccount = accountManager.depositOnAccount("123",BigDecimal.TEN,null);
        Assert.assertNull(actualAccount);
    }

    @Test
    public void withdrawalOnAccount() {
        Account account= new Account("123","user", BigDecimal.TEN) ;
        Account accountResult = new Account("123","user",BigDecimal.ZERO);
        Mockito.when(accountAction.substractAmountOperation(account,BigDecimal.TEN)).thenReturn(accountResult);
        Mockito.when(accountAction.findAccountById(accountsList,"123")).thenReturn(account);
        Account actualAccount = accountManager.withdrawalOnAccount("123",BigDecimal.TEN,accountsList);
        Assert.assertEquals(actualAccount,accountResult);
    }

    @Test
    public void withdrawalOnAccount_Empty() {
        Account actualAccount = accountManager.withdrawalOnAccount("123",BigDecimal.TEN,null);
        Assert.assertNull(actualAccount);
    }

    @Test
    public void withdrawalOnAccount_Null() {
        Account actualAccount = accountManager.withdrawalOnAccount("123",BigDecimal.ZERO,accountsList);
        Assert.assertNull(actualAccount);
    }
}
