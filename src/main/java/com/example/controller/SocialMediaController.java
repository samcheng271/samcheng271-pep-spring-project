package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

import com.example.service.MessageService;
import com.example.service.AccountService;
import com.example.entity.Account;
import com.example.entity.Message;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    // private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
        // this.messageService = messageService;
    }

    @PostMapping("register")
    public ResponseEntity<Account> regesterUserController (@RequestBody Account account){
        Account registeredAcc = accountService.registerAcc(account);
        if (registeredAcc!=null){
            if(registeredAcc.getUsername()=="duplicate"){
                return ResponseEntity.status(409).body(account);
            }
            return ResponseEntity.ok(registeredAcc);
        }
        return ResponseEntity.status(400).body(account);
    }
}
