package com.waa.bank.repository;

import com.waa.bank.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer> {
    Account findAccountByAccountNumber(int accountNumber);
}

