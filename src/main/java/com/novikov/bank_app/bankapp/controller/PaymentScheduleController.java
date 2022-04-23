package com.novikov.bank_app.bankapp.controller;


import com.novikov.bank_app.bankapp.models.BankClient;
import com.novikov.bank_app.bankapp.models.Credit;
import com.novikov.bank_app.bankapp.models.Offer;
import com.novikov.bank_app.bankapp.models.PaymentSchedule;
import com.novikov.bank_app.bankapp.service.BankClientService;
import com.novikov.bank_app.bankapp.service.CreditService;
import com.novikov.bank_app.bankapp.service.OfferService;
import com.novikov.bank_app.bankapp.service.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/payment-schedule")
public class PaymentScheduleController {

    private final PaymentScheduleService paymentScheduleService;
    private final OfferService offerService;
    private final BankClientService bankClientService;
    private final CreditService creditService;

    @Autowired
    public PaymentScheduleController(PaymentScheduleService paymentScheduleService, OfferService offerService, BankClientService bankClientService, CreditService creditService) {
        this.paymentScheduleService = paymentScheduleService;
        this.offerService = offerService;
        this.bankClientService = bankClientService;
        this.creditService = creditService;
    }

    @GetMapping("/")
    public String getPaymentSchedule(@ModelAttribute("offer") Offer offer, Model model) {
        Credit credit = creditService.findById(offer.getCredit().getId());
        BankClient client = bankClientService.getBankClient(offer.getBankClient().getId());
        offer.setCredit(credit);
        offer.setBankClient(client);
        LocalDate date = LocalDate.now();
        List<PaymentSchedule> paymentScheduleList = paymentScheduleService.infoPaymentSchedule(offer, date);
        paymentScheduleService.saveAll(paymentScheduleList);
        model.addAttribute("paymentScheduleList", paymentScheduleList);
        return "payment-schedule/payment-schedule";
    }

    @GetMapping("/{id}")
    public String showPaymentSchedule(@PathVariable("id") UUID id, Model model) {
        Offer offer = offerService.findById(id);
        Credit credit = creditService.findById(offer.getCredit().getId());
        BankClient bankClient = bankClientService.getBankClient(offer.getBankClient().getId());
        offer.setCredit(credit);
        offer.setBankClient(bankClient);
        List<PaymentSchedule> paymentScheduleList = paymentScheduleService.findByOfferOrderByDate(offer);
        model.addAttribute("offer", offer);
        model.addAttribute("paymentScheduleList", paymentScheduleList);
        return "payment-schedule/payment-schedule";
    }

}
