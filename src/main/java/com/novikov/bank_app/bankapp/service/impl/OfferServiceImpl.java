package com.novikov.bank_app.bankapp.service.impl;

import com.novikov.bank_app.bankapp.dao.OfferRepository;
import com.novikov.bank_app.bankapp.models.Credit;
import com.novikov.bank_app.bankapp.models.Offer;
import com.novikov.bank_app.bankapp.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public void save(Offer offer) {
    offerRepository.save(offer);
    }

    @Override
    public Offer findById(UUID id) {
        Offer offer = null;
        Optional<Offer> optional = offerRepository.findById(id);
        if (optional.isPresent()) {
            offer = optional.get();
        }
        return offer;
    }

    @Override
    public void delete(UUID id) {
        offerRepository.deleteById(id);
    }
}
