package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.BadRequestException;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message) {
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

    public Message getMessageByMessageId(Integer message_id) {
        //return messageRepository.getById(message_id);
        return messageRepository.getMessageByMessageId(message_id);
    }

    public Integer deleteMessageGivenMessageId(Integer message_id) {
        /* 
        if (messageRepository.getById(message_id) == null) 
            throw new BadRequestException("User not found");

        messageRepository.delete(messageRepository.getById(message_id));

        return 1;
        */
        //Message message = messageRepository.getById(message_id);
        if (messageRepository.getById(message_id) != null) {
        //if () {
            messageRepository.delete(messageRepository.getById(message_id));
            return 1;
        }
        return 0;
    }

    public Integer updateMessageText(Integer message_id, String message_text) {
        if (message_text == "")
            throw new BadRequestException("New message_text is empty");

        if (message_text.length() >= 255)
            throw new BadRequestException("New message_text is over 255 characters");

        int updatedRows = messageRepository.updateMessageText(message_id, message_text);
        if (updatedRows == 0) {
            throw new BadRequestException("Message not found with id " + message_id);
        }
        return updatedRows;
    }

    public List<Message> getAllMessagesByAccountId(Integer account_id) {
        return messageRepository.getAllMessagesByAccountId(account_id);
    }
}
