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
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message addMessage(Message message) throws Exception {
        if (message.getMessage_text() == "") 
            throw new BadRequestException("Message text is blank");
        
        if (message.getMessage_text().length() >= 255)
            throw new BadRequestException("Message text is not under 255 characters");
        
        if (accountRepository.getById(message.getPosted_by()) == null)
            throw new BadRequestException("User not found");

        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
