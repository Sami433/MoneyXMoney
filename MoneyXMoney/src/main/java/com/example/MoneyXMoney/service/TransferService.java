package com.example.MoneyXMoney.service;

import com.example.MoneyXMoney.model.Account;
import com.example.MoneyXMoney.model.Transfer;
import com.example.MoneyXMoney.model.User;
import com.example.MoneyXMoney.repository.AccountRepository;
import com.example.MoneyXMoney.repository.ConnectionRepository;
import com.example.MoneyXMoney.repository.TransferRepository;
import com.example.MoneyXMoney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    public void saveTransfer(Transfer transfer) throws Exception {

        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();

        int id = userService.findUserId(username);
        User from = userRepository.findById(id);



        int id2 = transfer.getTo().getId();
        User to = userRepository.findById(id2);



        transfer.setDate(LocalDateTime.now());
        transfer.setFrom(from);
        transfer.setTo(to);
        double amountTransfer= transfer.getAmountTransfer();
        if (transfer.getFrom().getAccount().getAmount() >= amountTransfer) {
            transfer.setAmountTransfer(amountTransfer);
        } else {
            throw new Exception("Not enough money");
        }
        transferRepository.save(transfer);


       Account accountSender = transfer.getFrom().getAccount();
        double amount = accountSender.getAmount() - transfer.getAmountTransfer();
        accountRepository.setAmountByUserId(amount, accountSender.getIban(), id);

        Account accountReceiver = transfer.getTo().getAccount();
        double amount2 = accountReceiver.getAmount() + transfer.getAmountTransfer();
        accountRepository.setAmountByUserId(amount2, accountReceiver.getIban(), id2);

    }
}


