package org.example.accountservice.service;

import org.example.accountservice.dto.AccountDetailsDTO;
import org.example.accountservice.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account saveAccount(Account account);
    void deleteAccount(Long id);
    AccountDetailsDTO getAccountDetails(Long accountId);
}