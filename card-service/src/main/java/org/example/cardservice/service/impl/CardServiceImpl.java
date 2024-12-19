package org.example.cardservice.service.impl;

import org.example.cardservice.dto.AccountDTO;
import org.example.cardservice.entity.Card;
import org.example.cardservice.repository.CardRepository;
import org.example.cardservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public List<Card> getCardsByAccountId(Long accountId) {
        return cardRepository.findByAccountId(accountId);
    }

    public Card saveCard(Card card) {
        String accountServiceUrl = "http://localhost:8090/accounts/" + card.getAccountId();
        AccountDTO account = restTemplate.getForObject(accountServiceUrl, AccountDTO.class);
        if (account != null) {
            return cardRepository.save(card);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}