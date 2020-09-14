package com.example.WatchNext.controller;

import com.example.WatchNext.payload.request.SignupRequest;
import com.example.WatchNext.payload.response.MessageResponse;
import com.example.WatchNext.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final AuthService authService;

    @Autowired
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (authService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username or email is already taken!"));
        }
        if (authService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: Username or email is already taken!"));
        }

        var val = authService.register(signUpRequest);
        return ResponseEntity.ok(new MessageResponse(val));
    }
}

