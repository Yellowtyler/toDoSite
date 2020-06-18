package com.example.toDo.rest;

import com.example.toDo.entity.ERole;
import com.example.toDo.entity.Role;
import com.example.toDo.payload.request.SignupRequest;
import com.example.toDo.repository.RoleRepository;
import com.example.toDo.repository.UserRepository;
import com.example.toDo.security.jwt.JwtUtils;
import com.example.toDo.service.imp.UserDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private PasswordEncoder encoder;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @WithMockUser(value = "spring")
    @Test
    void registerUser200() throws Exception {
        Mockito.when(userRepository.existsByLogin("user")).thenReturn(false);
        Mockito.when(userRepository.existsByEmail("user@sa.ru")).thenReturn(false);
        Role role = new Role(ERole.ROLE_USER);
        Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("user");
        signupRequest.setPassword("123456789");
        signupRequest.setEmail("user@sa.ru");
        ObjectMapper objectMapper = new ObjectMapper();
        String signupStr = objectMapper.writeValueAsString(signupRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
        .content(signupStr)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


    }
}