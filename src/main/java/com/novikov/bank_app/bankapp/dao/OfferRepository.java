package com.novikov.bank_app.bankapp.dao;

import com.novikov.bank_app.bankapp.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
}
