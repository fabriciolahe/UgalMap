package eu.crystalsystem.ugalmap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int userId);

	List<User> findAllByUserEmail(String userEmail);
	
	User findUserByUserEmailAndUserPasswd(String userEmail, String userPasswd);
}
