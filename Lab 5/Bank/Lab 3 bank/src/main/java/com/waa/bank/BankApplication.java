package com.waa.bank;

import com.waa.bank.model.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BankApplication implements CommandLineRunner {

    private final RestTemplate restTemplate;

    private static String url = "http://localhost:8083/account";

    public BankApplication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //add new account
        ResponseEntity<?> account = restTemplate.exchange(url + "?accountNumber=1&accountHolder=Ate", HttpMethod.POST, null, new ParameterizedTypeReference<Account>() {
        });
        System.out.println("--------New Account is store-----------");
        System.out.println(account);
        //get account by id
        ResponseEntity<?> accounts = restTemplate.exchange(url + "/1", HttpMethod.GET, null, new ParameterizedTypeReference<Account>() {
        });
        System.out.println("--------get all accounts-------------");
        System.out.println(accounts);
        //deposit
        ResponseEntity<?> deposit = restTemplate.exchange(url + "/deposit?accountNumber=1&amount=-60", HttpMethod.POST, null, new ParameterizedTypeReference<Account>() {
        });
        System.out.println("--------get deposit amount-------------");
        System.out.println(deposit);
        //withdraw
        ResponseEntity<?> withdraw = restTemplate.exchange(url + "/withdraw?accountNumber=1&amount=-60", HttpMethod.POST, null, new ParameterizedTypeReference<Account>() {
        });
        System.out.println("--------get withdraw amount-------------");
        System.out.println(withdraw);
        //remove account
        restTemplate.delete(url + "/1");//exchange(url + "/1", HttpMethod.DELETE, null, new ParameterizedTypeReference<Account>() {});
        System.out.println("--------delete Account-------------");
        System.out.println(accounts);
    }
}

