package com.novikov.bank_app.bankapp.service;

import com.novikov.bank_app.bankapp.models.Offer;
import com.novikov.bank_app.bankapp.models.PaymentSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PaymentScheduleService {

    List<PaymentSchedule> findAll();

    public void save(PaymentSchedule paymentSchedule);

    public PaymentSchedule findById(UUID id);

    public void delete(UUID id);

    List<PaymentSchedule> infoPaymentSchedule(Offer creditOffer, LocalDate date);

    void saveAll(List<PaymentSchedule> paymentScheduleList);

    List<PaymentSchedule> findByOfferOrderByDate(Offer offer);
}
