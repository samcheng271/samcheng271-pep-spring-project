package com.example.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.MessageRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import com.example.entity.Message;
import com.example.exception.*;
import javax.security.sasl.AuthenticationException;

@Service
@Transactional
public class MessageService {

    private MessageRepository msgRepository;

    @Autowired
    public MessageService(MessageRepository msgRepository){
        this.msgRepository = msgRepository;
    }

    public Message newMessage(Message msg){
        if(msg.getMessageText().length()>255 || msg.getMessageText().isEmpty() || !msgRepository.findByPostedBy(msg.getPostedBy()).isPresent()){
            // System.out.println("outofbounds::\n\n\n\n:");
            return null;
        } else{
            Message msgInDB = msgRepository.save(msg);
            msg.setMessageId(msgInDB.getMessageId());
            return msg;
        }

        // return null;
    }
}
