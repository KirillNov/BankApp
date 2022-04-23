package com.novikov.bank_app.bankapp.service;

import com.novikov.bank_app.bankapp.models.BankClient;

import java.util.List;
import java.util.UUID;

public interface BankClientService {
    List<BankClient> findAll();

    public void saveBankClient(BankClient bankClient);

    public BankClient getBankClient(UUID id);

    public void deleteBankClient(UUID id);

}
