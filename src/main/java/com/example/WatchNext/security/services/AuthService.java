package com.example.WatchNext.security.services;

import com.example.WatchNext.model.User;
import com.example.WatchNext.payload.request.LoginRequest;
import com.example.WatchNext.payload.request.ResetPasswordRequest;
import com.example.WatchNext.payload.request.SignupRequest;

import java.util.Optional;


public interface AuthService {

    String authenticate(LoginRequest loginRequest);

    Optional<User> findUserByEmail(String email);

    void resetPass(ResetPasswordRequest resetPasswordRequest);

    String register(SignupRequest signupRequest);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
