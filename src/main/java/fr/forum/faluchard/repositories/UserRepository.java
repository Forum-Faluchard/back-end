package fr.forum.faluchard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.forum.faluchard.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
