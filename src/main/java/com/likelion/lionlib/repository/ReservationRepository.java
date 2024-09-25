package com.likelion.lionlib.repository;

import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByMember(Member member);
    Long countAllByBookId(Long bookId);
}
