package com.example.MoneyXMoney.service;

import com.example.MoneyXMoney.model.Account;
import com.example.MoneyXMoney.model.Transfer;
import com.example.MoneyXMoney.model.User;
import com.example.MoneyXMoney.repository.AccountRepository;
import com.example.MoneyXMoney.repository.TransferRepository;
import com.example.MoneyXMoney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransferRepository transferRepository;

    public AccountService() {
    }

    public void add(Account account) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();

        Optional<User> user = userRepository.findByEmail(username);
        int id = user.get().getId();
        double amount = user.get().getAccount().getAmount() + account.getAmount();
        String iban = account.getIban();
        if (amount > 5000) throw new ArithmeticException("EXCESSIVE AMOUNT");
        else {
            accountRepository.setAmountByUserId(amount, iban, id);

        }

    }

    public void withdraw(Account account) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        Optional<User> user = userRepository.findByEmail(username);
        int id = user.get().getId();
        double amount = user.get().getAccount().getAmount() - account.getAmount();
        String iban = account.getIban();
        if (amount < 0) throw new ArithmeticException("INSUFFICIENT AMOUNT ON YOUR ACCOUNT");
        else {
            accountRepository.setAmountByUserId(amount, iban, id);


        }


    }

    public void shop (Account account) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        Optional<User> user = userRepository.findByEmail(username);
        int id = user.get().getId();
        double amount = user.get().getAccount().getAmount() - 20;
        String iban = account.getIban();
        if (amount < 0) throw new ArithmeticException("INSUFFICIENT AMOUNT ON YOUR ACCOUNT");
        else {
            accountRepository.setAmountByUserId(amount, iban, id);


        }

    }
}

