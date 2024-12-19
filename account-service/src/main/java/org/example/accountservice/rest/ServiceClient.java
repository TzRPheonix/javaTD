package org.example.accountservice.rest;

import org.example.accountservice.dto.CardDTO;
import org.example.accountservice.dto.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public List<CardDTO> getCardsForAccount(Long accountId) {
        String cardServiceUrl = "http://localhost:8080/cards/account/" + accountId;
        CardDTO[] cards = restTemplate.getForObject(cardServiceUrl, CardDTO[].class);
        return Arrays.asList(cards);
    }

    public List<LoanDTO> getLoansForAccount(Long accountId) {
        String loanServiceUrl = "http://localhost:8080/loans/account/" + accountId;
        LoanDTO[] loans = restTemplate.getForObject(loanServiceUrl, LoanDTO[].class);
        return Arrays.asList(loans);
    }
}