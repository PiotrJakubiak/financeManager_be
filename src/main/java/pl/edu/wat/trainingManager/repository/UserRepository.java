package pl.edu.wat.trainingManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.trainingManager.model.User;

/**
 * Created by Piotr on 19.10.2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
