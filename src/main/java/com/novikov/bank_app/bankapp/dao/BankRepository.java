package com.novikov.bank_app.bankapp.dao;

import com.novikov.bank_app.bankapp.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {
}
