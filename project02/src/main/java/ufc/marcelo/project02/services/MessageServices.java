package ufc.marcelo.project02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufc.marcelo.project02.models.Message;
import ufc.marcelo.project02.models.User;
import ufc.marcelo.project02.repositories.IMessageRepository;

import java.util.List;

@Service
public class MessageServices {

    private final IMessageRepository messageRepository;

    @Autowired
    public MessageServices(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void Add(String content, User sender, User receiver) {

        Message message = new Message(content, sender, receiver);

        messageRepository.save(message);
    }

    public List<Message> getAll(int senderId, int receiverId) {
        return messageRepository.findMessagesBySenderIdAndReceiverId(senderId, receiverId);
    }
}
