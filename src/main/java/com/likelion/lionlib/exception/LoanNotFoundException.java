package com.likelion.lionlib.exception;

import com.likelion.lionlib.domain.Loan;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException() {
        super("Loan 기록을 찾을 수 없습니다.");
    }

    public LoanNotFoundException(Long loanId){
        super("Loan: " + loanId + "를 찾을 수 없습니다.");
    }
}
