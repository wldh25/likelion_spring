package com.likelion.lionlib.repository;

import com.likelion.lionlib.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}