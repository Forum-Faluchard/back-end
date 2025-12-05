package fr.forum.faluchard.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import fr.forum.faluchard.model.User;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestUserService {
    
    @Autowired
    private UserService userService;

    @Test
    public void testValidCreate() {
        String expectedUsername = "user";
        String expectedPassword = "1234";

        try {
            User user = userService.create(expectedUsername, expectedPassword);
            assertEquals(expectedUsername, user.getUsername());
            assertEquals(expectedPassword, user.getPassword());
        } catch(RuntimeException ignore) {
            fail("Valid user creation must raise no error");
        }
    }

    @Test
    public void testUsernameAlreadyTaken() {
        String username = "user";
        String password = "0000";

        try {
            userService.create(username, password);
        } catch(RuntimeException ignore) {
            fail("Valid user creation must raise no error");
        }

        try {
            userService.create(username, password);
            fail("It is not possible to create a user with a username already taken");
        } catch(RuntimeException ignore) {
        }
    }

}
