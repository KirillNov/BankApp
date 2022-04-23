package com.novikov.bank_app.bankapp.dao;

import com.novikov.bank_app.bankapp.models.BankClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankClientRepository extends JpaRepository<BankClient, UUID> {
}
