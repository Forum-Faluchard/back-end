package fr.forum.faluchard.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import fr.forum.faluchard.errors.HttpErrorCode;

import org.json.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class TestUserController {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void signupValid() throws Exception {
        String expectedUsername = "newUser";
        String expectedPassword = "newUser548?";

        MvcResult result = mvc.perform(
            post("/users/signup")
            .param("username", expectedUsername)
            .param("password", expectedPassword))
            .andExpect(status().isOk())
            .andReturn();

        JSONObject json = new JSONObject(
            result.getResponse().getContentAsString()
        );

        assertEquals(expectedUsername, json.getString("username"));
        assertEquals(expectedPassword, json.getString("password"));
    }

    @Test
    public void signupUsernameTaken() throws Exception {
        String expectedUsername = "superAdmin";

        MvcResult result = mvc.perform(
                post("/users/signup")
                .param("username", expectedUsername)
                .param("password", "0000")
            )
            .andExpect(status().isBadRequest())
            .andReturn();

        JSONObject json = new JSONObject(
            result.getResponse().getContentAsString()
        );

        assertEquals(expectedUsername, json.getJSONObject("details").getString("username"));
        assertEquals(HttpErrorCode.USERNAME_TAKEN.ordinal(), json.getInt("errorCode"));
    }

}
