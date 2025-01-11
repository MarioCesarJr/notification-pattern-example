package com.errors.test_errors_list;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateUserWhenValidData() throws Exception {
        String userJson = "{ \"name\": \"Mazaropi da Silva\", \"email\": \"mazaropi.silva@example.com\" }";

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mazaropi da Silva"))
                .andExpect(jsonPath("$.email").value("mazaropi.silva@example.com"));
    }

    @Test
    void shouldReturnBadRequestWhenNameIsMissing() throws Exception {
        String userJson = "{ \"name\": null, \"email\": \"mazaropi.silva@example.com\" }";

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors[0]").value("Name is required"));
    }

    @Test
    void shouldReturnBadRequestWhenEmailIsMissing() throws Exception {
        String userJson = "{ \"name\": \"Mazaropi da Silva\", \"email\": null }";

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors[0]").value("Email is required"));
    }

    @Test
    void shouldReturnBadRequestWhenBothNameAndEmailAreMissing() throws Exception {
        String userJson = "{ \"name\": null, \"email\": null }";

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors[0]").value("Name is required"))
                .andExpect(jsonPath("$.errors[1]").value("Email is required"));
    }
}
