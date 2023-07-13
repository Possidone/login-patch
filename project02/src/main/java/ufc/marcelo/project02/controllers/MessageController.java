package ufc.marcelo.project02.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufc.marcelo.project02.dto.requests.message.GetMessages;
import ufc.marcelo.project02.dto.requests.message.SendMessage;
import ufc.marcelo.project02.dto.responses.GenericResult;
import ufc.marcelo.project02.models.Message;
import ufc.marcelo.project02.models.User;
import ufc.marcelo.project02.repositories.IUserRepository;
import ufc.marcelo.project02.services.MessageServices;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageServices messageServices;
    private final IUserRepository userRepository;

    @Autowired
    public MessageController(MessageServices messageServices, IUserRepository userRepository) {
        this.messageServices = messageServices;
        this.userRepository = userRepository;
    }

    @PostMapping("/send")
    public ResponseEntity<GenericResult> Send(@RequestBody SendMessage sendMessage) {

        try {
            Optional<User> sender = userRepository.findById(sendMessage.sender());

            if(sender.isEmpty()) {

                GenericResult result = new GenericResult(false, "Sender not found", null);

                return ResponseEntity.badRequest().body(result);
            }

            Optional<User> receiver = userRepository.findById(sendMessage.receiver());

            if(receiver.isEmpty()) {
                GenericResult result = new GenericResult(false, "Receiver not found", null);

                return ResponseEntity.badRequest().body(result);
            }

            messageServices.Add(sendMessage.message(), sender.get(), receiver.get());

            return ResponseEntity.ok().body(new GenericResult(true, "Message sent", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResult(false, "Error", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> GetMessages(@RequestParam("sender") int sender, @RequestParam("receiver") int receiver) {

        try {
            List<Message> messages = messageServices.getAll(sender, receiver);

            return ResponseEntity.ok().body(messages);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new LinkedList<>());
        }
    }
}
