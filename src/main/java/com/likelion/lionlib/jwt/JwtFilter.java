package com.likelion.lionlib.jwt;

import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.dto.CustomUserDetails;
import com.likelion.lionlib.repository.MemberRepository;
import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nullable HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        //request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.info("wrong token");
            filterChain.doFilter(request, response);
            return;
        }
        log.info("authorization now");

        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];
        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            log.info("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        //토큰에서 email 획득
        String email = jwtUtil.getEmail(token);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        //스프링 시큐리티 인증 토큰 생성
        Authentication accessToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(accessToken);

        filterChain.doFilter(request, response);
    }
}