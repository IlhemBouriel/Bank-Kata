package com.kata.bank.kataBank.controller;

import com.kata.bank.kataBank.entity.Account;
import com.kata.bank.kataBank.manager.AccountManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AccountControllerTest {
    @Mock
    private AccountManager accountManager;
    private  AccountController accountController;
    private List<Account> accountsList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountController = new AccountController();
        accountController.setAccountManager(accountManager);
        accountsList = Arrays.asList(new Account("23455", "user1", BigDecimal.TEN),
                new Account("1247", "user2", BigDecimal.ONE));
    }

    @Test
    public void getAccountListTest() {
        List<Account> actualList = accountController.getAccountList();
        Assert.assertEquals(actualList.size(),accountsList.size());
        Assert.assertEquals(actualList.get(0).getIdAccount(),actualList.get(0).getIdAccount());
    }


}
