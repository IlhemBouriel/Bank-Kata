package com.kata.bank.kataBank.controller;

import com.kata.bank.kataBank.entity.Account;
import com.kata.bank.kataBank.entity.AccountHistory;
import com.kata.bank.kataBank.manager.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class AccountController {

    @Autowired
    private AccountManager accountManager;

    private static List accountsList = new ArrayList<Account>();

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @PostConstruct
    public List<Account> getAccountList() {
        accountsList.add(new Account("23455", "user1", new BigDecimal(Math.random() * (80000 - 100))));
        accountsList.add(new Account("1247", "user2", new BigDecimal(Math.random() * (80000 - 100))));
        return accountsList;
    }

    // should be visited to create accounts list
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllAccounts() {
        return new ResponseEntity<>(
                accountsList,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAccount(@PathVariable(value = "accountId") String accountId) {
        if (Objects.nonNull(accountId)) {
            Account account = this.accountManager.findAccountById(accountId, accountsList);
            if (Objects.isNull(account)) {
                return new ResponseEntity<>(
                        "Not Found",
                        HttpStatus.NOT_FOUND
                );
            } else {
                return new ResponseEntity<>(
                        account,
                        HttpStatus.OK
                );
            }
        } else
            return new ResponseEntity<>(
                    "Null",
                    HttpStatus.BAD_REQUEST
            );


    }

    //1 for desopit
    //2 for Withdrawal
    @RequestMapping(value = "/operation/{operationId}/{accountId}/{amount}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity depositOnAccount(@PathVariable(value = "operationId") int operationId, @PathVariable(value = "accountId") String accountId, @PathVariable(value = "amount") BigDecimal amount) {
        if (Objects.nonNull(accountId)) {
            Account account = this.accountManager.makeOperationOnAccount(accountId, amount, operationId, accountsList);
            if (Objects.isNull(account)) {
                return new ResponseEntity<>(
                        "Not Found",
                        HttpStatus.NOT_FOUND
                );
            } else {
                return new ResponseEntity<>(
                        account,
                        HttpStatus.OK
                );
            }
        } else {
            return new ResponseEntity<>(
                    "Null",
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    @RequestMapping(value = "/history/{accountId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAccountHistory(@PathVariable(value = "accountId") String accountId) {
        if (Objects.nonNull(accountId)) {

            List<AccountHistory> history = this.accountManager.historyAccount(accountId);
            if (Objects.isNull(history))
                return new ResponseEntity<>(
                        "No history found",
                        HttpStatus.NOT_FOUND
                );
            else
                return new ResponseEntity<>(
                        history,
                        HttpStatus.OK
                );
        } else {
            return new ResponseEntity<>(
                    "Null",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
