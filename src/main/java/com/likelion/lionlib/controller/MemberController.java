package com.likelion.lionlib.controller;

import com.likelion.lionlib.dto.LoginRequest;
import com.likelion.lionlib.dto.SignupRequest;
import com.likelion.lionlib.service.MemberService;
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
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> joinProcess(@RequestBody SignupRequest signupRequest) {
        log.info("signup email: {}", signupRequest.getEmail());
        memberService.joinProcess(signupRequest);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        log.info("login email: {}", loginRequest.getEmail());
        boolean isSuccess = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (isSuccess) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 잘못된 이메일 또는 비밀번호");
        }
    }
}
