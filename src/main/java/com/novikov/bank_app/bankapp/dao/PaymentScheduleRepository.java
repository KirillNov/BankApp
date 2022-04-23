package com.novikov.bank_app.bankapp.dao;

import com.novikov.bank_app.bankapp.models.Offer;
import com.novikov.bank_app.bankapp.models.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, UUID> {
    List<PaymentSchedule> findByOfferOrderByDate(Offer offer);
}
