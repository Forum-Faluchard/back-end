package fr.forum.faluchard.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import fr.forum.faluchard.exceptions.UsernameTakenException;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class TestService {
    
    @Autowired
    private UserService userService;

    @Test
    public void createValid() {
        String expectedUsername = "newUser";
        String expectedPassword = "0000";
        try {
            User user = userService.create(expectedUsername, expectedPassword);
            assertEquals(expectedUsername, user.getUsername());
            assertEquals(expectedPassword, user.getPassword());
        } catch(UsernameTakenException ex) {
            fail("Username " + ex.getUsername() + " should be available");
        }
    }

    @Test
    public void createUsernameTaken() {
        String expectedUsername = "superAdmin";
        try {
            userService.create(expectedUsername, "0000");
            fail("Username " + expectedUsername + " should not be available");
        } catch(UsernameTakenException ex) {
            assertEquals(expectedUsername, ex.getUsername());
        }
    }

}
