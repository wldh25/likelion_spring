package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.dto.SignupRequest;
import com.likelion.lionlib.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입 처리
    public void joinProcess(SignupRequest signupRequest) {
        // 이메일 중복 체크
        if (memberRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // 회원 생성
        Member newMember = Member.builder()
                .email(signupRequest.getEmail())
                .password(signupRequest.getPassword()) // 패스워드는 암호화 해야 함
                .build();
        memberRepository.save(newMember);
    }

    // 로그인 처리
    public boolean login(String email, String password) {
        // 이메일로 회원 검색
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // 패스워드 확인 (패스워드는 암호화되어야 함)
        return member.getPassword().equals(password);
    }
}