package ufc.marcelo.project02.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @ManyToOne()
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne()
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private Date createdAt;

    public Message(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.createdAt = new Date();
    }
}
