package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.BadRequestException;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message) throws BadRequestException {
        if (message.getMessage_text() == "") 
            throw new BadRequestException("Message text is blank");
        
        if (message.getMessage_text().length() >= 255)
            throw new BadRequestException("Message text is not under 255 characters");
        
        if (messageRepository.postedByRefersToRealExistingUser(message.getPosted_by()) == null)
            throw new BadRequestException("User not found");

        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(int message_id) { //throws BadRequestException {
        /*
        if (messageRepository.getById(message_id) == null) {
            throw new BadRequestException("Message not found");
        }
        */
        return messageRepository.getById(message_id);
            //.orElseThrow(() -> new BadRequestException("Message not found"));
    }

    public Integer deleteMessageGivenMessageId(Integer message_id) throws BadRequestException {
        if (messageRepository.getById(message_id) == null) 
            throw new BadRequestException("User not found");

        messageRepository.delete(messageRepository.getById(message_id));

        return 1;
    }

    public List<Message> getAllMessagesByAccountId(Integer account_id) {
        return messageRepository.getAllMessagesByAccountId(account_id);
    }
}
