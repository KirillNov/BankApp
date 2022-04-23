package com.novikov.bank_app.bankapp.service.impl;

import com.novikov.bank_app.bankapp.dao.BankClientRepository;
import com.novikov.bank_app.bankapp.models.BankClient;
import com.novikov.bank_app.bankapp.service.BankClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankClientServiceImpl implements BankClientService {


    private final BankClientRepository bankClientRepository;

    @Autowired
    public BankClientServiceImpl(BankClientRepository bankClientRepository) {
        this.bankClientRepository = bankClientRepository;
    }

    @Override
    public List<BankClient> findAll() {
        return bankClientRepository.findAll();
    }

    @Override
    public void saveBankClient(BankClient bankClient) {
        bankClientRepository.save(bankClient);

    }

    @Override
    public BankClient getBankClient(UUID id) {
        BankClient bankClient = null;
        Optional<BankClient> optional = bankClientRepository.findById(id);
        if (optional.isPresent()) {
            bankClient = optional.get();
        }
        return bankClient;
    }

    @Override
    public void deleteBankClient(UUID id) {
        bankClientRepository.deleteById(id);
    }
}
