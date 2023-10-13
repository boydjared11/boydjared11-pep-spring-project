package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;
import com.example.entity.Account;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "FROM Account WHERE account_id = :posted_by")
    Account postedByRefersToRealExistingUser(@Param("posted_by") Integer posted_by);

    @Query(value = "FROM Message WHERE posted_by = :account_id")
    List<Message> getAllMessagesByAccountId(@Param("account_id") Integer account_id);

    @Query(value = "FROM Message WHERE message_id = :message_id")
    Message getMessageByMessageId(@Param("message_id") Integer message_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Message SET message_text = :message_text WHERE message_id = :message_id")
    int updateMessageText(@Param("message_id") Integer message_id, @Param("message_text") String message_text);
}
