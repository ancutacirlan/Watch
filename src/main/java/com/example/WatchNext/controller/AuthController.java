package com.example.WatchNext.controller;

import com.example.WatchNext.model.User;
import com.example.WatchNext.payload.request.LoginRequest;
import com.example.WatchNext.payload.request.ResetPasswordRequest;
import com.example.WatchNext.payload.response.JwtResponse;
import com.example.WatchNext.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String jwt = authService.authenticate(loginRequest);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        Optional<User> val = authService.findUserByEmail(resetPasswordRequest.getEmail());
        val.ifPresentOrElse(
                user -> {
                    authService.resetPass(user);
                    new ResponseEntity(HttpStatus.OK);
                },
                () -> new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
}
