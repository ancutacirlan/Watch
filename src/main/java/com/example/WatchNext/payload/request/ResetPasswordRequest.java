package com.example.WatchNext.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordRequest {

    private String email;

}
