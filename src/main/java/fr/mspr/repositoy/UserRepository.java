package fr.mspr.repositoy;

import fr.mspr.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
	User findUserByPseudoAndPassword(String pseudo, String password);
}
