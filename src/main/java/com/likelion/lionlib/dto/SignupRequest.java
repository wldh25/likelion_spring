package com.likelion.lionlib.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SignupRequest {
    private String email;
    private String password;
}