package com.likelion.lionlib.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.likelion.lionlib.domain.LoanStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoanRequest {
    private Long memberId;
    private Long bookId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus status;
}