package com.example.repository;

// import entity.Message;
import com.example.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Integer> {
}
