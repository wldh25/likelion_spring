package com.likelion.lionlib.controller;

import com.likelion.lionlib.dto.*;
import com.likelion.lionlib.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberService memberService;

    // 회원 정보 조회
    @GetMapping("/members")
    public ResponseEntity<MemberResponse> getMember(Authentication authentication) {
        Long memberId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        log.info("Request GET member with ID: {}", memberId);
        MemberResponse memberResponse = memberService.findMember(memberId);
        return ResponseEntity.ok(memberResponse);
    }

    // 프로필 수정
    @PutMapping("/members")
    public ResponseEntity<MemberResponse> putMember(Authentication authentication, @RequestBody ProfileRequest updateRequest) {
        Long memberId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        log.info("Request PUT update member with ID: {}", memberId);
        MemberResponse updatedMember = memberService.updateMember(memberId, updateRequest);
        return ResponseEntity.ok(updatedMember);
    }
}