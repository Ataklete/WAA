package com.waa.bank.controller;


import com.waa.bank.model.Account;
import com.waa.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class BankController {
//createAccount(int accountNumber, String accountHolder);
//deposit(int accountNumber, amount);
//withdraw(int accountNumber, amount);
//getAccount(int accountNumber);
//removeAccount(int accountNumber);

    @Autowired
    AccountService accountService;

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> createAccount(@RequestParam("accountNumber") int accountNumber,
                                           @RequestParam("accountHolder") String accountHolder){
        boolean bo = accountService.createAccount(accountNumber,accountHolder);
        return new ResponseEntity<>(bo,HttpStatus.OK);
    }

    @GetMapping(value = "/{accountNumber}", produces = "application/json")
    public ResponseEntity<?> getAccount(@PathVariable("accountNumber") int accountNumber){
        Account account = accountService.getAccount(accountNumber);
        if (account == null) {
            return ResponseEntity.badRequest().body("There is no Account has an account number equal to " + accountNumber);
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @GetMapping("/health")
    public String CheckHealth(){
        return "\"success\": true";
    }
}
