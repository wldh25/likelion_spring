package com.likelion.lionlib.repository;

import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByMember(Member member);
}