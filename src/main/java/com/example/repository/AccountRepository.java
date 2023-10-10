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
//public class AccountRepository {
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("FROM Account WHERE username = :username")
    Account findByUsername(@Param("username") String username);
    /* 
    private final List<Account> accounts = new ArrayList<>();

	Map<Integer, Account> mapOfAccounts = new HashMap<Integer, Account> ();

    private Integer accountId = 1000;

    public Optional<Account> save(Account account) {
    	//p.setId(productSerialId++);
        account.setAccount_id(accountId--);
    	//products.add(p);
        accounts.add(account);
    	//return Optional.of(p);
        return Optional.of(account);
    }

    public Optional<Account> findByUsername(String username) {
    	//return products.stream().filter(product -> product.getId().equals(id)).findFirst();
        return accounts.stream().filter(account -> account.getUsername().equals(username)).findFirst();
    }
    */
}
