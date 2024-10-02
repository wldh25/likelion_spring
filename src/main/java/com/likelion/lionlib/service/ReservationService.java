package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Loan;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.dto.*;
import com.likelion.lionlib.exception.ReservationNotFoundException;
import com.likelion.lionlib.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final GlobalService globalService;

    public ReservateResponse addReservation(CustomUserDetails customUserDetails, ReservateRequest reservateRequest) {
        Member member = globalService.findMemberById(customUserDetails.getId());
        Book book = globalService.findBookById(reservateRequest.getBookId());
        Reservation savedReservation = Reservation.builder()
                .member(member)
                .book(book)
                .build();
        reservationRepository.save(savedReservation);
        return ReservateResponse.fromEntity(savedReservation);
    }

    public ReservateResponse getReservation(Long reservationId) {
        Reservation reservation = findReservationById(reservationId);
        return ReservateResponse.fromEntity(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<ReservateResponse> getReservationsByMemberId(Long memberId) {
        List<Reservation> reservations = findReservationsByMemberId(memberId);
        return reservations.stream()
                .map(ReservateResponse::fromEntity)
                .collect(Collectors.toList());
    }

    private Reservation findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }

    private List<Reservation> findReservationsByMemberId(Long memberId) {
        Member member = globalService.findMemberById(memberId);
        return reservationRepository.findAllByMember(member);
    }

    public ReservationsResponse getBookReservations(Long bookId) {
        Book book = globalService.findBookById(bookId);
        return ReservationsResponse.builder()
                .count(reservationRepository.countAllByBookId(bookId))
                .build();
    }
}
