package com.example.WatchNext.security.services;

import com.example.WatchNext.model.User;
import com.example.WatchNext.payload.request.SignupRequest;
import com.example.WatchNext.repositories.UserRepository;
import com.example.WatchNext.security.jwt.GeneratePassword;
import com.example.WatchNext.security.jwt.JwtUtils;
import com.example.WatchNext.security.jwt.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceImplTest {

    AuthService authService;
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;
    PasswordEncoder encoder;
    SendEmail sendEmail;
    GeneratePassword generatePassword;
    RoleService roleService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authService = new AuthServiceImpl(authenticationManager,jwtUtils,userRepository,
                encoder,sendEmail,generatePassword,roleService);
    }


    @Test
    void authenticate() {
    }

    @Test
    void resetPassword() {

    }

    @Test
    void register() {

    }

    @Test
    void existsByUsername() {
        User user = mock(User.class);
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
        assertEquals(true,authService.existsByUsername(user.getUsername()));
    }

    @Test
    void existsByEmail() {
        User user = mock(User.class);
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
        assertEquals(true,authService.existsByEmail(user.getEmail()));
    }

    @Test
    void findUserByEmail() {
        User user = mock(User.class);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertEquals(user.getEmail(),authService.findUserByEmail(user.getEmail()).get().getEmail());
    }
}