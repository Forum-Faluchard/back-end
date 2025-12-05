package fr.forum.faluchard.services;

import fr.forum.faluchard.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import fr.forum.faluchard.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User create(String username, String password) throws RuntimeException {        
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return userRepository.save(user);
        } catch(DataIntegrityViolationException e) {
            throw new RuntimeException("Nom d'utilisateur déjà pris");
        }
    }

}
