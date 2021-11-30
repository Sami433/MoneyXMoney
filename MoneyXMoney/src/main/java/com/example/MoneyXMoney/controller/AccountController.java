package com.example.MoneyXMoney.controller;

import com.example.MoneyXMoney.model.Account;
import com.example.MoneyXMoney.repository.AccountRepository;
import com.example.MoneyXMoney.service.AccountService;
import com.example.MoneyXMoney.service.CustomUserDetailsService;
import com.example.MoneyXMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/account")
    public String accountForm(Model model, Account account) {
        model.addAttribute("account", account);
        return "account";
    }

    @GetMapping("/process_adding_account")
    public String processAddingAccount(Account account) {
        accountService.add(account);
        return "add_account_success";
    }
    @GetMapping("/process_withdraw_account")
    public String processWithdrawAccount(Account account) {
        accountService.withdraw(account);
        return "add_account_success";
    }

    @GetMapping("/process_shop")
    public String processShop(Account account) {
        accountService.shop(account);
        return "buy_product_success";
    }


}
