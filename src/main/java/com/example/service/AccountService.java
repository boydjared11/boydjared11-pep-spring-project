package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.BadRequestException;
import com.example.exception.ConflictException;
import com.example.exception.UnauthorizedException;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account) {
        if (account.getUsername() == "")
    		throw new BadRequestException("Username cannot be blank");

    	if (account.getPassword().length() < 4)
    		throw new BadRequestException("Password is not at least 4 characters long");

        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new ConflictException("Username already exists");
        }
        return accountRepository.save(account);
    }
    
    public Account verifyUserGivenUsernameAndPassword(String username, String password) {
        if (accountRepository.getUserGivenUsernameAndPassword(username, password) == null)
            throw new UnauthorizedException("Invalid username and/or password");
        return accountRepository.getUserGivenUsernameAndPassword(username, password);
    }
}
