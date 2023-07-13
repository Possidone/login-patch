package ufc.marcelo.project02.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String gender;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Message> messages;

    public User(String name, String urlImage, String email, String password, String gender) {
        this.name = name;
        this.urlImage = urlImage;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
