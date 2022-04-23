package com.novikov.bank_app.bankapp.service;

import com.novikov.bank_app.bankapp.models.Bank;

import java.util.List;
import java.util.UUID;

public interface BankService {
    List<Bank> findAll();

    public void save(Bank bank);

    public Bank findById(UUID id);

    public void delete(UUID id);

}
