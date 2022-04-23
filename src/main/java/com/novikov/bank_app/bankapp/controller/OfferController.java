package com.novikov.bank_app.bankapp.controller;

import com.novikov.bank_app.bankapp.models.*;
import com.novikov.bank_app.bankapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final BankClientService bankClientService;
    private final CreditService creditService;
    private final BankService bankService;
    private final PaymentScheduleService paymentScheduleService;

    @Autowired
    public OfferController(OfferService offerService, BankClientService bankClientService, CreditService creditService, BankService bankService, PaymentScheduleService paymentScheduleService) {
        this.offerService = offerService;
        this.bankClientService = bankClientService;
        this.creditService = creditService;
        this.bankService = bankService;
        this.paymentScheduleService = paymentScheduleService;
    }


    @GetMapping ("/")
    public String newOffer (@ModelAttribute ("offer") Offer offer, Model model) {
        List<BankClient> clientList = bankClientService.findAll();
        List<Credit> creditList = creditService.findAll();
        model.addAttribute("clients", clientList);
        model.addAttribute("credits", creditList);
        model.addAttribute("path", "offer/");
        model.addAttribute("name", "Оформление кредита");
        model.addAttribute("add", "Оформить кредит");


        return "offer/offer";
    }

    @PostMapping("/")
    public String createOffer(@ModelAttribute ("offer") @Valid Offer offer, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()) {
            List<BankClient> clientList = bankClientService.findAll();
            List<Credit> creditList = creditService.findAll();
            model.addAttribute("clients", clientList);
            model.addAttribute("credits", creditList);
            model.addAttribute("path", "offer/");
            model.addAttribute("name", "Оформление кредита");
            model.addAttribute("add", "Оформить кредит");
            return "offer/offer";
        }
        offerService.save(offer);
        Bank bank = new Bank();
        bank.setBankClient(offer.getBankClient());
        bank.setCredit(offer.getCredit());
        bank.setOffer(offer);
        bankService.save(bank);
        redirectAttributes.addFlashAttribute(offer);
        return "redirect:/payment-schedule/";
    }

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable("id") UUID id, Model model) {
        Offer offer = offerService.findById(id);
        List<BankClient> clientList = bankClientService.findAll();
        List<Credit> creditList = creditService.findAll();
        model.addAttribute("path", "offer/edit");
        model.addAttribute("name", "Изменение кредитного предложения");
        model.addAttribute("credits", creditList);
        model.addAttribute("clients", clientList);
        model.addAttribute("offer", offer);
        model.addAttribute("add", "Изменить кредит");
        return "offer/offer";

    }

    @PostMapping("/edit")
    public String updateOffer(@ModelAttribute ("offer") @Valid Offer offer, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("path", "offer/edit");
            model.addAttribute("name", "Изменение кредитного предложения");
            model.addAttribute("add", "Изменить кредит");

            return "offer/offer";
        }
        offerService.save(offer);
        List<PaymentSchedule> paymentScheduleList = paymentScheduleService.findByOfferOrderByDate(offer);
        for (PaymentSchedule paymentSchedule : paymentScheduleList) {
            paymentScheduleService.delete(paymentSchedule.getId());
        }
        redirectAttributes.addFlashAttribute(offer);
        return "redirect:/payment-schedule/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable("id") UUID id) {
        offerService.delete(id);
        return "redirect:/bank/";
    }

}
