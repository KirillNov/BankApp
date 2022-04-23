package com.novikov.bank_app.bankapp.controller;

import com.novikov.bank_app.bankapp.models.BankClient;
import com.novikov.bank_app.bankapp.models.Credit;
import com.novikov.bank_app.bankapp.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/credits")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/")
    public String showAllCredits(Model model) {
        List<Credit> credits = creditService.findAll();
        model.addAttribute("credits", credits);
        return "credits/credits";
    }

    @GetMapping("/new")
    public String newCredit(@ModelAttribute ("credit") Credit credit, Model model) {
        model.addAttribute("path", "new/");
        model.addAttribute("name", "Новый кредит");
        model.addAttribute("add", "Добавить");
        return "credits/new";
    }

    @PostMapping("/new")
    public String createCredit(@ModelAttribute ("credit") @Valid Credit credit, BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("path", "new/");
            model.addAttribute("name", "Новый кредит");
            model.addAttribute("add", "Добавить");
            return "credits/new";
        }
        creditService.save(credit);
        return "redirect:/credits/";
    }
    @GetMapping("/edit/{id}")
    public String editCredit(@PathVariable("id")UUID id, Model model) {
        Credit credit = creditService.findById(id);
        model.addAttribute("path", "edit");
        model.addAttribute("name", "Изменить кредит");
        model.addAttribute("credit", credit);
        model.addAttribute("add", "Изменить");
        return "credits/new";
    }

    @PostMapping("/edit")
    public String updateCredit(@ModelAttribute ("credit") @Valid Credit credit, BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("path", "edit");
            model.addAttribute("name", "Изменить кредит");
            model.addAttribute("add", "Изменить");


            return "credits/new";
        }
        creditService.save(credit);
        return "redirect:/credits/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCredit(@PathVariable("id") UUID id) {
        creditService.delete(id);
        return "redirect:/credits/";
    }



}
