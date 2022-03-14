package com.waa.bank.controller;


import com.waa.bank.model.Account;
import com.waa.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class BankController {

    @Autowired
    AccountService accountService;

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> createAccount(@Valid @RequestParam("accountNumber") int accountNumber,
                                           @RequestParam("accountHolder") String accountHolder, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        boolean bo = accountService.createAccount(accountNumber,accountHolder);
        return new ResponseEntity<>(bo,HttpStatus.OK);
    }

    @GetMapping(value = "/{accountNumber}", produces = "application/json")
    public ResponseEntity<?> getAccount(@Valid @PathVariable("accountNumber") int accountNumber, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Account account = accountService.getAccount(accountNumber);
        if (account == null) {
            return ResponseEntity.badRequest().body("There is no Account has an account number equal to " + accountNumber);
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }
    @DeleteMapping(value = "/{accountNumber}", produces = "application/json")
    public ResponseEntity<?> removeAccount(@Valid @PathVariable int accountNumber, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Account account = accountService.getAccount(accountNumber);
        if (account == null) {
            return ResponseEntity.badRequest().body("There is no account has an account number equal to " + accountNumber);
        }
        accountService.removeAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Account is deleted successfully");
    }

    @PostMapping(value = "/deposit", produces = "application/json")
    public ResponseEntity<?> deposit(@Valid @RequestParam("accountNumber") int accountNumber,
                                     @RequestParam("amount") double amount, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        accountService.deposit(accountNumber,amount);
        return ResponseEntity.status(HttpStatus.OK).body(amount + " dollar deposit to account Number "+ accountNumber + " successfully");
    }

    @PostMapping(value = "/withdraw", produces = "application/json")
    public ResponseEntity<?> withdraw(@Valid @RequestParam("accountNumber") int accountNumber,
                                     @RequestParam("amount") double amount, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        accountService.withdraw(accountNumber,amount);
        return ResponseEntity.status(HttpStatus.OK).body(amount + " dollar deposit to account Number "+ accountNumber + " successfully");
    }

    @GetMapping("/health")
    public String CheckHealth(){
        return "\"success\": true";
    }
}
