package com.epam.jmp.jmpm7springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.jmp.jmpm7springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * AuthenticationControllerTest
 * Date: 03/14/2023
 *
 * @author Yauheni Antsipenka
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegister() throws Exception {
        String userToRegister = "{\"login\": \"test3\",\"password\": \"test3\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/register")
                    .content(userToRegister)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        assertEquals("OK", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testAuth() throws Exception {
        String userToRegister = "{\"username\": \"user\",\"password\": \"user\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth")
                    .content(userToRegister)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        String token = "{\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjgwMDM3MjAwfQ.9acvQ6EosDhunT3mPherHRkbw_ViIasZDkd6qsLY74ThUcD4eR9ODTAS4X-1klYeq2uTuvmLY2DWjHQoNo7Upg\"}";
        assertEquals(token, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testAuthWhenUserNotInDB() throws Exception {
        String userToRegister = "{\"username\": \"incorrect_user\",\"password\": \"incorrect_password\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth")
                    .content(userToRegister)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().isEmpty());
    }
}
