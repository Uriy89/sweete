package main.repo;

import main.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepo extends CrudRepository<Message, Long> {
    public List<Message> findByTag(String tag);
}
