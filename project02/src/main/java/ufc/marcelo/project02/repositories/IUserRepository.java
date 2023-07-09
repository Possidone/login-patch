package ufc.marcelo.project02.repositories;

import org.springframework.data.repository.CrudRepository;
import ufc.marcelo.project02.models.User;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByEmailAndPassword(String email, String password);
}
