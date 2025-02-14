package com.example.repository;

// import entity.Message;
import com.example.entity.Message;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message,Integer> {
    Optional<Message> findByPostedBy(Integer postedBy);
}
