package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.exception.BookNotFoundException;
import com.likelion.lionlib.exception.MemberNotFoundException;
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
                .orElseThrow(BookNotFoundException::new);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));
    }
}