package com.waa.bank.controller;


import com.waa.bank.dto.AccountAdaptor;
import com.waa.bank.dto.AccountDTO;
import com.waa.bank.model.Account;
import com.waa.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class BankController {

    @Autowired
    AccountService accountService;

    AccountAdaptor adaptor = new AccountAdaptor();

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> createAccount(@RequestParam("accountNumber") int accountNumber,
                                           @RequestParam("accountHolder") String accountHolder){
        Account bo = accountService.createAccount(accountNumber,accountHolder);
        AccountDTO accountDTO = adaptor.getAccountDTO(bo);
        return new ResponseEntity<>(accountDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/{accountNumber}", produces = "application/json")
    public ResponseEntity<?> getAccount(@PathVariable("accountNumber") int accountNumber){
        AccountDTO accountDTO = accountService.getAccount(accountNumber);
        if (accountDTO == null) {
            return ResponseEntity.badRequest().body("There is no Account has an account number equal to " + accountNumber);
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }
    @DeleteMapping(value = "/{accountNumber}", produces = "application/json")
    public Object removeAccount(@PathVariable int accountNumber){

        AccountDTO accountDTO = accountService.getAccount(accountNumber);
        if (accountDTO == null) {
            return ResponseEntity.badRequest().body("There is no account has an account number equal to " + accountNumber);
        }
        accountService.removeAccount(accountNumber);
//        return ResponseEntity.status(HttpStatus.OK).body("Account is deleted successfully");
        return "successful";
    }


    @PostMapping(value = "/deposit", produces = "application/json")
    public Object deposit(@RequestParam("accountNumber") int accountNumber,
                          @RequestParam("amount") double amount){
        AccountDTO deposit = accountService.deposit(accountNumber, amount);
        if (deposit == null) {
            return "There is no account has an account number equal to " + accountNumber;
        }
        return ResponseEntity.status(HttpStatus.OK).body(deposit);
    }


    @PostMapping(value = "/withdraw", produces = "application/json")
    public Object withdraw(@RequestParam("accountNumber") int accountNumber,
                           @RequestParam("amount") double amount){
        AccountDTO withdraw = accountService.withdraw(accountNumber,amount);
        if (withdraw == null) {
            return "There is no account has an account number equal to " + accountNumber;
        }
        return ResponseEntity.status(HttpStatus.OK).body(withdraw);
    }

    @GetMapping("/health")
    public String CheckHealth(){
        return "\"success\": true";
    }
}
