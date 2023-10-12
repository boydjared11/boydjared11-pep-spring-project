package com.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "FROM Account WHERE username = :username")
    Account findByUsername(@Param("username") String username);
    
    @Query(value = "FROM Account WHERE username = :username AND password = :password")
    Account getUserGivenUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
