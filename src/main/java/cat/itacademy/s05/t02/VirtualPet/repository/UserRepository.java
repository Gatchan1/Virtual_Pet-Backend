package cat.itacademy.s05.t02.VirtualPet.repository;

import cat.itacademy.s05.t02.VirtualPet.model.User;
import cat.itacademy.s05.t02.VirtualPet.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String userName);
    //Optional<User> findByEmail(String email);
    //Optional<User> findByRole(Role role);
}
