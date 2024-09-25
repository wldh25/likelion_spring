package com.likelion.lionlib.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoanStatus {
    LOANED("대출 중"),
    RETURNED("반납 완료"),
    OVERDUE("연체 중");

    private final String value;

    @JsonCreator
    public static LoanStatus deserializer(String value) {
        for(LoanStatus loanStatus : LoanStatus.values()){
            if(loanStatus.getValue().equals(value)) {
                return loanStatus;
            }
        }
        return null;
    }

    @JsonValue
    public String serializer(){
        return value;
    }
}