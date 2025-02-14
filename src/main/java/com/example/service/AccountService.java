package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import com.example.entity.Account;
import com.example.exception.*;
import javax.security.sasl.AuthenticationException;

@Service
@Transactional
public class AccountService {
    private AccountRepository accountRepo;

    @Autowired
    public AccountService(AccountRepository accountRepo){
        this.accountRepo = accountRepo;
    }

    public Account registerAcc(Account account){
        if (account.getUsername().isEmpty() || 
            account.getPassword().length() < 4) {
            return null;
        }
        if (accountRepo.existsByUsername(account.getUsername())){
            account.setUsername("duplicate");
            return account;
        }
        Account accInDB = accountRepo.save(account);
        account.setAccountId(accInDB.getAccountId());
        return account;
    }

    public Account loginAcc(Account account){
        String user = account.getUsername();
        String pass = account.getPassword();
        Optional<Account> optionalAcc = accountRepo.findByUsernameAndPassword(user,pass);
        if(optionalAcc.isPresent()){
            Account acc = optionalAcc.get();
            account.setAccountId(acc.getAccountId());
            return account;
        }
        return null;
    }
    // public Account loginAcc(Account account) throws AuthenticationException{
    //     String user = account.getUsername();
    //     String pass = account.getPassword();
    //     Account acc = accountRepo.findByUsernameAndPassword(user,pass).orElseThrow(() -> new AuthenticationException("User not found"));
    //     account.setAccountId(acc.getAccountId());
    //     return account;
    // }
}
