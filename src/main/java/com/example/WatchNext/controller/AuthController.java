package com.example.WatchNext.controller;

import com.example.WatchNext.model.User;
import com.example.WatchNext.payload.request.LoginRequest;
import com.example.WatchNext.payload.request.ResetPasswordRequest;
import com.example.WatchNext.payload.response.JwtResponse;
import com.example.WatchNext.payload.response.MessageResponse;
import com.example.WatchNext.repositories.UserRepository;
import com.example.WatchNext.security.jwt.GeneratePassword;
import com.example.WatchNext.security.jwt.JwtUtils;
import com.example.WatchNext.security.jwt.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final SendEmail sendEmail;
    private final GeneratePassword generatePassword;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository,
                          PasswordEncoder encoder, SendEmail sendEmail, GeneratePassword generatePassword) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.sendEmail = sendEmail;
        this.generatePassword = generatePassword;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody ResetPasswordRequest resetPasswordRequest) {

        if (userRepository.existsByEmail(resetPasswordRequest.getEmail())) {
            User users = userRepository.findByEmail(resetPasswordRequest.getEmail());
            String password = generatePassword.generateRandomPassword();
            users.setPassword(encoder.encode(password));
            userRepository.save(users);
            sendEmail.sendMail(users.getEmail(), "Reset password", password);
            return ResponseEntity.ok(new MessageResponse(
                    "Password reset was successful"));

        } else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email not found!"));


    }


}
