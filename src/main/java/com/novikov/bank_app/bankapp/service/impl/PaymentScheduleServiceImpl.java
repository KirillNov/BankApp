package com.novikov.bank_app.bankapp.service.impl;

import com.novikov.bank_app.bankapp.dao.PaymentScheduleRepository;
import com.novikov.bank_app.bankapp.models.Offer;
import com.novikov.bank_app.bankapp.models.PaymentSchedule;
import com.novikov.bank_app.bankapp.service.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentScheduleServiceImpl implements PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    @Autowired
    public PaymentScheduleServiceImpl(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }


    @Override
    public List<PaymentSchedule> findAll() {
        return paymentScheduleRepository.findAll();
    }

    @Override
    public void save(PaymentSchedule paymentSchedule) {
        paymentScheduleRepository.save(paymentSchedule);
    }

    @Override
    public PaymentSchedule findById(UUID id) {
        PaymentSchedule paymentSchedule = new PaymentSchedule();
        Optional<PaymentSchedule> optional = paymentScheduleRepository.findById(id);
        if(optional.isPresent()){
            paymentSchedule = optional.get();
        }
        return paymentSchedule;
    }

    @Override
    public void delete(UUID id) {
        paymentScheduleRepository.deleteById(id);
    }

    @Override
    public List<PaymentSchedule> infoPaymentSchedule(Offer creditOffer, LocalDate date) {
//        creditAmount - сумма кредита
//        loanDuration - продолжительность кредита в месяцах
//        interestRate - процентная ставка
//        totalMoney - Сумма погашения (основная сумма + проценты)
//        totalInterests - Общая сумма процентов;
        double totalMoney;
        double totalInterests;
        long creditAmount = creditOffer.getCreditAmount();
        int loanDuration = creditOffer.getLoanDuration();
        double interestRate = creditOffer.getCredit().getInterestRate();
        double mInterestRate = interestRate / 100 / 12;
        double pow = Math.pow(1 + mInterestRate,loanDuration);
        totalMoney = creditAmount * (mInterestRate + mInterestRate / (pow - 1));
        double scale = Math.pow(10, 2);
        totalMoney = Math.ceil(totalMoney * scale) / scale;
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();
        double amountRepaymentBody;
        double amountRepaymentInterest;
        for (int i = 0; i < loanDuration; i++) {
            date = date.plusMonths(1);
            amountRepaymentInterest = creditAmount * (interestRate / scale) * date.lengthOfMonth() / date.lengthOfYear();
            amountRepaymentInterest = Math.ceil(amountRepaymentInterest * scale) / scale;
            amountRepaymentBody = totalMoney - amountRepaymentInterest;
            amountRepaymentBody = Math.ceil(amountRepaymentBody * scale) / scale;
            creditAmount -= amountRepaymentBody;
            PaymentSchedule paymentSchedule = new PaymentSchedule(Date.valueOf(date), totalMoney, amountRepaymentBody, amountRepaymentInterest, creditOffer);
            paymentScheduleList.add(paymentSchedule);
        }
        return paymentScheduleList;
    }

    @Override
    public void saveAll(List<PaymentSchedule> paymentScheduleList) {
        paymentScheduleRepository.saveAll(paymentScheduleList);
    }

    @Override
    public List<PaymentSchedule> findByOfferOrderByDate(Offer offer) {
        return paymentScheduleRepository.findByOfferOrderByDate(offer);
    }

}
