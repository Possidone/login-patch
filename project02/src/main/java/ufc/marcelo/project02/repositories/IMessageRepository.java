package ufc.marcelo.project02.repositories;

import org.springframework.data.repository.CrudRepository;
import ufc.marcelo.project02.models.Message;

import java.util.List;

public interface IMessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findMessagesBySenderIdAndReceiverId(int sender, int receiver);

}
