package com.waa.bank.controller;

import com.waa.bank.dto.AccountDTO;
import com.waa.bank.model.Account;
import com.waa.bank.model.Transaction;
import com.waa.bank.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BankControllerTest {

    @Mock
    private AccountService service;

    @InjectMocks
    private BankController controller;

    Account account;
    AccountDTO accountDTO;


    @BeforeEach
    void setUp(){
        Transaction transaction = new Transaction();
        List<Transaction> transactions = new ArrayList<>();
        account = new Account("Ati",1,0.0);
        accountDTO = new AccountDTO("Ati",1,0.0);
    }

    @Test
    void createAccount() {
        Mockito.when(service.createAccount(1,"Ati")).thenReturn(account);
        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.createAccount(1,"Ati");

        Mockito.verify(service).createAccount(1,"Ati");
    }

    @Test
    void getAccount() {

        Mockito.when(service.getAccount(1)).thenReturn(accountDTO);
        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.getAccount(1);

        Mockito.verify(service).getAccount(1);
    }

    @Test
    void removeAccount() {
        Mockito.when(service.getAccount(1)).thenReturn(accountDTO);
        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.removeAccount(1);

        Mockito.verify(service).removeAccount(1);
    }

    @Test
    void deposit() {

        Mockito.when(service.deposit(1,100)).thenReturn(accountDTO);
        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.deposit(1,100);

        Mockito.verify(service).deposit(1,100);
    }

    @Test
    void withdraw() {

        Mockito.when(service.withdraw(1,100)).thenReturn(accountDTO);
        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.withdraw(1,100);

        Mockito.verify(service).withdraw(1,100);
    }
}