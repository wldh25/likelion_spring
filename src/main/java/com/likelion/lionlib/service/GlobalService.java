package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.repository.BookRepository;
import com.likelion.lionlib.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlobalService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }
}