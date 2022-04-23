package com.novikov.bank_app.bankapp.controller;

import com.novikov.bank_app.bankapp.models.Bank;
import com.novikov.bank_app.bankapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }


    @GetMapping("/")
    public String showBank(Model model) {
        List<Bank> bankList = bankService.findAll();
        model.addAttribute("bank", bankList);
        return "bank/bank";
    }

}
