package com.novikov.bank_app.bankapp.service;

import com.novikov.bank_app.bankapp.models.Bank;
import com.novikov.bank_app.bankapp.models.BankClient;
import com.novikov.bank_app.bankapp.models.Credit;
import com.novikov.bank_app.bankapp.models.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferService {

    List<Offer> findAll();

    public void save(Offer offer);

    public Offer findById(UUID id);

    public void delete(UUID id);


}
