package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        super();
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping(value = "/register")
    public @ResponseBody Account registerUser(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @PostMapping(value = "/login")
    public @ResponseBody Account loginUser(@RequestBody Account account) {
        return accountService.verifyUserGivenUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    @PostMapping(value = "/messages")
    public @ResponseBody Message createMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @GetMapping(value = "/messages")
    public @ResponseBody List<Message> retrieveAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping(value = "/messages/{message_id}")
    public @ResponseBody Message retrieveMessageByMessageId(@PathVariable Integer message_id) {
        return messageService.getMessageByMessageId(message_id);
    }

    @DeleteMapping(value = "/messages/{message_id}")
    public @ResponseBody String deleteMessageGivenMessageId(@PathVariable Integer message_id) {
        return messageService.deleteMessageGivenMessageId(message_id).toString();
    }

    @PatchMapping(value = "/messages/{message_id}")
    public @ResponseBody String updateMessage(@PathVariable Integer message_id, @RequestBody Message message) {
        return messageService.updateMessageText(message_id, message.getMessage_text()).toString();
    }

    @GetMapping(value = "/accounts/{account_id}/messages")
    public @ResponseBody List<Message> retrieveAllMessagesForUser(@PathVariable Integer account_id) {
        return messageService.getAllMessagesByAccountId(account_id);
    }
}
