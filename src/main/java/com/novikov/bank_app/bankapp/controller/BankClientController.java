package com.novikov.bank_app.bankapp.controller;

import com.novikov.bank_app.bankapp.models.BankClient;
import com.novikov.bank_app.bankapp.service.BankClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class BankClientController {

    private final BankClientService bankClientService;

    @Autowired
    public BankClientController(BankClientService bankClientService) {
        this.bankClientService = bankClientService;
    }

    @GetMapping("/")
    public String showAllBankClients (Model model) {
        List<BankClient> clients = bankClientService.findAll();
        model.addAttribute("clients", clients);
        return "clients/clients";
    }

    @GetMapping("/new")
    public String newBankClient (@ModelAttribute("client") BankClient bankClient, Model model) {
        model.addAttribute("path", "new/");
        model.addAttribute("name", "Новый клиент");
        model.addAttribute("add", "Добавить");
        return "clients/new";
    }

    @PostMapping("/new")
    public String createNewBankClient (@ModelAttribute ("client") @Valid BankClient bankClient,
                                       BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("path", "new/");
            model.addAttribute("name", "Новый клиент");
            model.addAttribute("add", "Добавить");
            return "clients/new";
        } else {
            bankClientService.saveBankClient(bankClient);
            return "redirect:/clients/";
        }
    }
    @GetMapping("/edit/{id}")
    public String editBankClient (Model model, @PathVariable("id") UUID id) {
        BankClient bankClient= bankClientService.getBankClient(id);
        model.addAttribute("path", "edit");
        model.addAttribute("client", bankClient);
        model.addAttribute("name", "Редактирование");
        model.addAttribute("add", "Сохранить");
        return "clients/new";
    }

    @PostMapping("/edit")
    public String updateBankClient (@ModelAttribute ("client") @Valid BankClient bankClient,
                                    BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("path", "edit");
            model.addAttribute("name", "Редактирование");
            model.addAttribute("add", "Сохранить");
            return "clients/new";
        } else {
            bankClientService.saveBankClient(bankClient);
            return "redirect:/clients/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBankClient(@PathVariable("id") UUID id) {
        bankClientService.deleteBankClient(id);
        return "redirect:/clients/";
    }




}
