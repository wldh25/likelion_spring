package com.likelion.lionlib.repository;

import com.likelion.lionlib.domain.Loan;
import com.likelion.lionlib.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByMember(Member member);
}