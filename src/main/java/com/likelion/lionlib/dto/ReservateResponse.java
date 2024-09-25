package com.likelion.lionlib.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.likelion.lionlib.domain.Reservation;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservateResponse {
    private Long memberId;
    private Long bookId;

    public static ReservateResponse fromEntity(Reservation reservation) {
        return ReservateResponse.builder()
                .memberId(reservation.getMember().getId())
                .bookId(reservation.getBook().getId())
                .build();
    }

}
