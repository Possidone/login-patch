package ufc.marcelo.project02.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String urlImage;
    private String email;
    private String password;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private List<Message> messages;

    public User(String name, String urlImage, String email, String password) {
        this.name = name;
        this.urlImage = urlImage;
        this.email = email;
        this.password = password;
    }
}
