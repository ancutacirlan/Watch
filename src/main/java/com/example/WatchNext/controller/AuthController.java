package com.example.WatchNext.controller;

import com.example.WatchNext.model.Users;
import com.example.WatchNext.security.jwt.GeneratePassword;
import com.example.WatchNext.payload.request.LoginRequest;
import com.example.WatchNext.payload.request.ResetPasswordRequest;
import com.example.WatchNext.security.jwt.SendEmail;
import com.example.WatchNext.payload.response.JwtResponse;
import com.example.WatchNext.payload.response.MessageResponse;
import com.example.WatchNext.repositories.UserRepository;
import com.example.WatchNext.security.jwt.JwtUtils;
import com.example.WatchNext.security.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private SendEmail sendEmail;
    private GeneratePassword generatePassword;


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
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> registerUser(@RequestBody ResetPasswordRequest resetPasswordRequest) {

        if (userRepository.existsByEmail(resetPasswordRequest.getEmail())) {
            Users users = userRepository.findByEmail(resetPasswordRequest.getEmail());
            String password = generatePassword.generateRandomPassword();
            System.out.println(password);
            if (generatePassword.isPasswordValid(password)) {
                System.out.println(users.getPassword());
                users.setPassword(encoder.encode(password));
                userRepository.save(users);
                System.out.println(users.getPassword());
                sendEmail.sendMail(users.getEmail(), "Reset password", password);
            }
            return ResponseEntity.ok(new MessageResponse(
                    "Password reset was successful"));

        } else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email not found!"));


    }


}
