package ufc.marcelo.project02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufc.marcelo.project02.dto.responses.user.SimpleUser;
import ufc.marcelo.project02.models.User;
import ufc.marcelo.project02.repositories.IUserRepository;

import java.util.ArrayList;

@Service
public class UserServices {

    private final IUserRepository userRepository;

    @Autowired
    public UserServices(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(String name, String urlImage, String email, String password, String gender) {
        User user = new User(name, urlImage, email, password, gender);
        userRepository.save(user);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean emailAndPasswordExists(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).isPresent();
    }

    public ArrayList<SimpleUser> getAll() {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();

        ArrayList<SimpleUser> simpleUsers = new ArrayList<>();

        for (User user : users) {

            SimpleUser simpleUser = new SimpleUser(user.getId(), user.getName(), user.getUrlImage(), user.getEmail(), user.getGender());

            simpleUsers.add(simpleUser);
        }

        return simpleUsers;
    }
}
