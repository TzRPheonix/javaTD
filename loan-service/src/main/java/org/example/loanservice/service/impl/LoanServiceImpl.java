package org.example.loanservice.service.impl;

import org.example.loanservice.entity.Loan;
import org.example.loanservice.service.LoanService;
import org.example.loanservice.dto.AccountDTO;
import org.example.loanservice.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public List<Loan> getLoansByAccountId(Long accountId) {
        return loanRepository.findByAccountId(accountId);
    }

    public Loan saveLoan(Loan loan) {
        System.out.println("Account ID: " + loan.getAccountId());
        String accountServiceUrl = "http://localhost:8090/accounts/" + loan.getAccountId();
        AccountDTO account = restTemplate.getForObject(accountServiceUrl, AccountDTO.class);
        if (account != null) {
            return loanRepository.save(loan);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}