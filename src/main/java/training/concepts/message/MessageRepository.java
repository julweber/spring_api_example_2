package training.concepts.message;

import training.concepts.message.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// use the imports below to formulate custom sql queries
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
  List<Message> findById(Long id);
  List<Message> findByRecipient(String email);

}
