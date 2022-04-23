package com.novikov.bank_app.bankapp.service.impl;

import com.novikov.bank_app.bankapp.dao.BankRepository;
import com.novikov.bank_app.bankapp.models.Bank;
import com.novikov.bank_app.bankapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public void save(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public Bank findById(UUID id) {
        Bank bank = new Bank();
        Optional<Bank> optional = bankRepository.findById(id);
        if (optional.isPresent()) {
            bank = optional.get();
        }
        return bank;
    }

    @Override
    public void delete(UUID id) {
        bankRepository.deleteById(id);
    }
}
