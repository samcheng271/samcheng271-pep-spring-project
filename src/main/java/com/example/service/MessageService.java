package com.example.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.MessageRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;


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
    }

    public List<Message> allMessages (){
        return (List)msgRepository.findAll();
    }

    public Message msgOfID(int msgID) {
        return msgRepository.findBymessageId(msgID);
    }

    public int delMessage(int msgID){
        if(msgRepository.existsById(msgID)){
            msgRepository.deleteById(msgID);
            return 1;
        }
        return 0;
    }
    public Integer updateMessage(Message newMsg, int msgId){
        Optional<Message> msgFound = msgRepository.findById(msgId);
        if(newMsg.getMessageText().length()>255 || newMsg.getMessageText().isEmpty() || !msgFound.isPresent()){
            // System.out.println("outofbounds::\n\n\n\n:");
            return 0;
        } else{
            Message theMsg = msgFound.get();
            theMsg.setMessageText(newMsg.getMessageText());
            msgRepository.save(theMsg);
            // msg.setMessageId(msgInDB.getMessageId());
            return 1;
        }
    }
    // public List<Message> allMessagesOfUser (String user){
    //     // return msgRepository.findAllByPostedBy(Integer.parseInt(user));
    // }
}
