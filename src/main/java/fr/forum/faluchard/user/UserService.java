package fr.forum.faluchard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.forum.faluchard.exceptions.UsernameTakenException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User create(String username, String password) throws UsernameTakenException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            return userRepository.save(user);
        } catch(Exception ignore) {
            UsernameTakenException exception = new UsernameTakenException();
            exception.setUsername(username);
            throw exception;
        }
    }

}
