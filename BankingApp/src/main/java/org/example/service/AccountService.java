package org.example.service;

import org.example.dao.AccountDao;
import org.example.model.Account;

import java.util.List;

public class AccountService {

    private AccountDao accountDao;


    public AccountService() {
        this.accountDao = new AccountDao();
    }


    public AccountService(AccountDao dao) {
        this.accountDao = dao;
    }


    public boolean createAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (account.getAccNo() <= 0) {
            throw new IllegalArgumentException("Invalid Account Number");
        }

        if (account.getName() == null || account.getName().isEmpty()) {
            throw new IllegalArgumentException("Account name is required");
        }

        if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        return accountDao.addAccount(account);
    }



    public List<Account> findAllAccounts() {
        return accountDao.findAllAccounts();
    }
}