package com.novikov.bank_app.bankapp.dao;

import com.novikov.bank_app.bankapp.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditsRepository extends JpaRepository<Credit, UUID> {
}
