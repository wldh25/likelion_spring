package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.dto.CustomUserDetails;
import com.likelion.lionlib.exception.MemberNotFoundException;
import com.likelion.lionlib.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("email{}", email);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));
        if (member != null) {
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(member);
        }
        return null;
    }
}