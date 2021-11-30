package com.example.MoneyXMoney.controller;

import com.example.MoneyXMoney.form.RegisterForm;
import com.example.MoneyXMoney.model.Account;
import com.example.MoneyXMoney.model.User;
import com.example.MoneyXMoney.repository.AccountRepository;
import com.example.MoneyXMoney.repository.UserRepository;
import com.example.MoneyXMoney.service.CustomUserDetailsService;
import com.example.MoneyXMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/home")
    public String UserProfile() {

        return "index";
    }

    @GetMapping("/register")
    public ModelAndView showRegistration(RegisterForm registerForm){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerForm", new RegisterForm());
        return modelAndView;
    }
    @PostMapping("/process_register")
    public ModelAndView processRegistration(User user){
        ModelAndView modelAndView = new ModelAndView("register_success");
        userService.saveUser(user);
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/about")
    public String about (){
        return "about";
    }

    @GetMapping("/shop")
    public String accountForm(Model model, Account account) {
        model.addAttribute("account", account);
        return "shop";
    }


    @GetMapping("/profile")
    public String profile(Model model){
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        Optional<User> user = userRepository.findByEmail(username);
        model.addAttribute("user", user);
        int id = userService.findUserId(username);
        Account account = accountRepository.findByUserId(id);
        model.addAttribute("account", account);
        return "profile";

    }




}