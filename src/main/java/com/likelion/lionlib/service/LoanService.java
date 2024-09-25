package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Loan;
import com.likelion.lionlib.domain.LoanStatus;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.dto.LoanRequest;
import com.likelion.lionlib.dto.LoanResponse;
import com.likelion.lionlib.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    private final GlobalService globalService;

    public LoanResponse addLoan(LoanRequest loanRequest) {
        Member member = globalService.findMemberById(loanRequest.getMemberId());
        Book book = globalService.findBookById(loanRequest.getBookId());
        Loan savedLoan = Loan.builder()
                .member(member)
                .book(book)
                .loanDate(loanRequest.getLoanDate())
                .returnDate(loanRequest.getReturnDate())
                .status(LoanStatus.LOANED)
                .build();
        loanRepository.save(savedLoan);
        return LoanResponse.fromEntity(savedLoan);
    }

    public LoanResponse getLoan(Long loanId) {
        Loan loan = findLoanById(loanId);
        return LoanResponse.fromEntity(loan);
    }

    public LoanResponse updateStatus(Long loanId, LoanRequest loanRequest) {
        Loan loan = findLoanById(loanId);
        loan.setStatus(loanRequest.getStatus());
        Loan updatedLoan = loanRepository.save(loan);
        return LoanResponse.fromEntity(updatedLoan);
    }

    public List<LoanResponse> getLoansByMemberId(Long memberId) {
        List<Loan> loans = findLoansByMemberId(memberId);
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }

    private Loan findLoanById(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    private List<Loan> findLoansByMemberId(Long memberId) {
        Member member = globalService.findMemberById(memberId);
        return loanRepository.findAllByMember(member);
    }
}