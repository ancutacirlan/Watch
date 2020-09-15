package com.example.WatchNext.security.services;

import com.example.WatchNext.model.User;
import com.example.WatchNext.payload.request.LoginRequest;
import com.example.WatchNext.payload.request.SignupRequest;
import com.example.WatchNext.repositories.UserRepository;
import com.example.WatchNext.security.jwt.GeneratePassword;
import com.example.WatchNext.security.jwt.JwtUtils;
import com.example.WatchNext.security.jwt.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final SendEmail sendEmail;
    private final GeneratePassword generatePassword;
    private final RoleService roleService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                           UserRepository userRepository, PasswordEncoder encoder, SendEmail sendEmail,
                           GeneratePassword generatePassword, RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.sendEmail = sendEmail;
        this.generatePassword = generatePassword;
        this.roleService = roleService;
    }


    @Override
    public String authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return jwt;
    }

    @Override
    public void resetPass(User user) {
            String password = generatePassword.generateRandomPassword();
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
            sendEmail.sendMail(user.getEmail(), "Reset password", password);
    }

    @Override
    public String register(SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        String strRoles = signUpRequest.getRole();
        var role = roleService.findRoleByName(strRoles);
        user.setRole(role);
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
