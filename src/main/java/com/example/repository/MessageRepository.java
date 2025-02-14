package com.example.repository;

// import entity.Message;
import com.example.entity.Message;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Integer> {
    Optional<Message> findByPostedBy(Integer postedBy);

    Message findBymessageId(int msgId);

    // void deleteBymessageId(int msgID);
    // @Query("SELECT m FROM Message m WHERE m.posted_by = :postedBy")
    List<Message> findAllByPostedBy(int postedBy);
    
    // @Query("SELECT m FROM Message m WHERE m.posted_by = :postedBy AND m.time_posted_epoch = :timePosted AND m.message_text = :messageText")
    // Optional<Message> findByPostedByAndTimeAndText(
    //     @Param("postedBy") int postedBy,
    //     @Param("timePosted") long timePosted,
    //     @Param("messageText") String messageText
    // );
}
