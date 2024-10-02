package com.likelion.lionlib.controller;

import com.likelion.lionlib.dto.CustomUserDetails;
import com.likelion.lionlib.dto.ReservateRequest;
import com.likelion.lionlib.dto.ReservateResponse;
import com.likelion.lionlib.dto.ReservationsResponse;
import com.likelion.lionlib.service.AuthService;
import com.likelion.lionlib.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReservationController {
    private final ReservationService reservationService;

    // 도서 예약 등록
    @PostMapping("/reservations")
    public ResponseEntity<ReservateResponse> addReservation(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody ReservateRequest reservateRequest) {
        log.info("Request POST a reservation: {}", reservateRequest);
        ReservateResponse savedReservation = reservationService.addReservation(customUserDetails,reservateRequest);
        log.info("Response POST a reservation: {}", savedReservation);
        return ResponseEntity.ok(savedReservation);
    }

    //예약 정보 조회
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservateResponse> getReservation(@PathVariable Long reservationId){
        log.info("Request GET a reservation with ID: {}", reservationId);
        ReservateResponse reservation = reservationService.getReservation(reservationId);
        log.info("Response GET a reservation: {}", reservation);
        return ResponseEntity.ok(reservation);
    }

    //예약 취소
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId){
        log.info("Request DELETE reservation with ID: {}", reservationId);
        reservationService.deleteReservation(reservationId);
        log.info("Response DELETE reservation with ID:{}", reservationId);
        return ResponseEntity.noContent().build();
    }

    //사용자 예약 목록 조회
    @GetMapping("/members/reservations")
    public ResponseEntity<List<ReservateResponse>> getReservationsByMemberId(Authentication authentication){
        log.info("Request GET all reservations");
        Long memberId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        List<ReservateResponse> reservations = reservationService.getReservationsByMemberId(memberId);
        log.info("Response GET all reservations: {}", reservations);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/books/{bookId}/reservations")
    public ResponseEntity<ReservationsResponse> getBookReservations(@PathVariable Long bookId) {
        log.info("Request GET reservations about a book: {}", bookId);
        ReservationsResponse bookReservations = reservationService.getBookReservations(bookId);
        return ResponseEntity.ok(bookReservations);
    }

}
