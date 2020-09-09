package com.example.WatchNext.controller;

import com.example.WatchNext.model.Role;
import com.example.WatchNext.model.Users;
import com.example.WatchNext.payload.request.SignupRequest;
import com.example.WatchNext.payload.response.MessageResponse;
import com.example.WatchNext.repositories.RoleRepository;
import com.example.WatchNext.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Users user = new Users(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRoles = signUpRequest.getRole();
        if (strRoles == null || strRoles.equals("user")) {
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRole(userRole);
        } else if (strRoles.equals("admin")) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            user.setRole(adminRole);
        } else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Role is not found"));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

