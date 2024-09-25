package com.likelion.lionlib.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.likelion.lionlib.domain.Loan;
import com.likelion.lionlib.domain.LoanStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoanResponse {
    private Long memberId;
    private Long bookId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public static LoanResponse fromEntity(Loan loan) {
        return LoanResponse.builder()
                .memberId(loan.getMember().getId())
                .bookId(loan.getBook().getId())
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .status(loan.getStatus())
                .build();
    }
}