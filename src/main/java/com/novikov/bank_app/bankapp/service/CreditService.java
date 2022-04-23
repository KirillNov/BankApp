package com.novikov.bank_app.bankapp.service;

import com.novikov.bank_app.bankapp.models.BankClient;
import com.novikov.bank_app.bankapp.models.Credit;

import java.util.List;
import java.util.UUID;


public interface CreditService {
    List<Credit> findAll();

    public void save(Credit credit);

    public Credit findById(UUID id);

    public void delete(UUID id);

}
