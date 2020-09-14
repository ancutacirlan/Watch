package com.example.WatchNext.payload.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {

    private String username;
    private String email;
    private String role;
    private String password;

}
