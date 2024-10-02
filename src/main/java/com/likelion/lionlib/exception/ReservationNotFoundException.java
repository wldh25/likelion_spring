package com.likelion.lionlib.exception;

import com.likelion.lionlib.dto.ReservationsResponse;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException() {
        super("Reservation을 찾을 수 없습니다.");
    }

    public ReservationNotFoundException(Long reservationId) {
        super("Reservation: " + reservationId + " 을 찾을 수 없습니다.");
    }
}