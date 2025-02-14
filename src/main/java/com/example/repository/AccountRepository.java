package com.example.repository;
import com.example.entity.Account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer>{
    
    boolean existsByUsername(String Username);

    Optional<Account> findByUsernameAndPassword(String Username, String getPassword);
}
