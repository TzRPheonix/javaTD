package org.example.accountservice.service.impl;

import org.example.accountservice.dto.AccountDetailsDTO;
import org.example.accountservice.dto.CardDTO;
import org.example.accountservice.dto.LoanDTO;
import org.example.accountservice.entity.Account;
import org.example.accountservice.repository.AccountRepository;
import org.example.accountservice.rest.ServiceClient;
import org.example.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ServiceClient serviceClient;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public AccountDetailsDTO getAccountDetails(Long accountId) {
        Account account = getAccountById(accountId);

        List<CardDTO> cards = serviceClient.getCardsForAccount(accountId);
        List<LoanDTO> loans = serviceClient.getLoansForAccount(accountId);

        AccountDetailsDTO accountDetails = new AccountDetailsDTO();
        accountDetails.setId(account.getId());
        accountDetails.setName(account.getName());
        accountDetails.setEmail(account.getEmail());
        accountDetails.setCards(cards);
        accountDetails.setLoans(loans);

        return accountDetails;
    }
}