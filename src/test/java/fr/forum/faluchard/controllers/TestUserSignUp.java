package fr.forum.faluchard.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestUserSignUp {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void testValidSignUp() throws Exception {
        String username = "user";
        String password = "1234";

        mvc.perform(post("/users/signup")
            .param("username", username)
            .param("password", password))
            .andExpect(status().isOk());
    }

    @Test
    public void testUsernameAlreadyTaken() throws Exception {
        String username = "user";
        String password = "1234";

        mvc.perform(post("/users/signup")
            .param("username", username)
            .param("password", password))
            .andExpect(status().isOk());

        mvc.perform(post("/users/signup")
            .param("username", username)
            .param("password", password))
            .andExpect(status().isBadRequest());
    }
}
