package com.likelion.lionlib.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // Many쪽이 주인이라고 생각하면 됨
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private LocalDate loanDate;
    private LocalDate returnDate;

    @Setter
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
}
