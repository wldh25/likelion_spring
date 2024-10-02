package com.likelion.lionlib.controller;

import com.likelion.lionlib.dto.SignupRequest;
import com.likelion.lionlib.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        log.info("signup email: {}", signupRequest.getEmail());
        authService.joinProcess(signupRequest);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }
}