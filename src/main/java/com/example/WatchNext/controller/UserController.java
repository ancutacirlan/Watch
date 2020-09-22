package com.example.WatchNext.controller;

import com.example.WatchNext.model.User;
import com.example.WatchNext.payload.request.ResetPasswordRequest;
import com.example.WatchNext.payload.request.SignupRequest;
import com.example.WatchNext.payload.response.MessageResponse;
import com.example.WatchNext.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (authService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: Email is already taken!"));
        }

        var val = authService.register(signUpRequest);
        return ResponseEntity.ok(new MessageResponse(val));
    }


    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        Optional<User> val = authService.findUserByEmail(resetPasswordRequest.getEmail());
        val.ifPresentOrElse(
                user -> {
                    authService.resetPassword(user);
                    new ResponseEntity(HttpStatus.OK);
                },
                () -> new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
}

