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
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
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

    @PostMapping("login")
    public ResponseEntity<Account> loginUserController (@RequestBody Account account){
        Account loggedAccount = accountService.loginAcc(account);
        if (loggedAccount!=null){
            return ResponseEntity.ok(loggedAccount);
        }
        return ResponseEntity.status(401).body(account);
    }

    @PostMapping("messages")
    public ResponseEntity<Message> newMessage (@RequestBody Message message){
        Message createdMsg = messageService.newMessage(message);
        if (createdMsg!=null){
            return ResponseEntity.ok(createdMsg);
        }
        return ResponseEntity.status(400).body(message);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> allMessages (){
        return ResponseEntity.ok(messageService.allMessages());

    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> msgOfMsgID (@PathVariable String messageId){
        return ResponseEntity.ok(messageService.msgOfID(Integer.parseInt(messageId)));
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> delMessage (@PathVariable String messageId){
        if(messageService.delMessage(Integer.parseInt(messageId))==1){
        return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> updateMessage (@PathVariable String messageId, @RequestBody Message message){
        if(messageService.updateMessage(message, Integer.parseInt(messageId))==1)
            return ResponseEntity.status(200).body(1);
        
        return ResponseEntity.status(400).build();

    }
    
    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> allMessagesOfUser (@PathVariable String accountId){
        return ResponseEntity.ok(messageService.allMessagesOfUser(accountId));
    }
}
