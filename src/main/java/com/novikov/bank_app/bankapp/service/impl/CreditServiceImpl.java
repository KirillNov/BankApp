package com.novikov.bank_app.bankapp.service.impl;

import com.novikov.bank_app.bankapp.dao.CreditsRepository;
import com.novikov.bank_app.bankapp.models.BankClient;
import com.novikov.bank_app.bankapp.models.Credit;
import com.novikov.bank_app.bankapp.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditServiceImpl implements CreditService {

    private final CreditsRepository creditsRepository;

    @Autowired
    public CreditServiceImpl(CreditsRepository creditsRepository) {
        this.creditsRepository = creditsRepository;
    }

    @Override
    public List<Credit> findAll() {
        return creditsRepository.findAll();
    }

    @Override
    public void save(Credit credit) {
        creditsRepository.save(credit);
    }

    @Override
    public Credit findById(UUID id) {
        Credit credit = null;
        Optional<Credit> optional = creditsRepository.findById(id);
        if (optional.isPresent()) {
            credit = optional.get();
        }
        return credit;
    }

    @Override
    public void delete(UUID id) {
        creditsRepository.deleteById(id);
    }
}
