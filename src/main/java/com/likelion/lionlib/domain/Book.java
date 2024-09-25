package com.likelion.lionlib.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;
    private String category;
    private String author;
    private String publisher;
    private String isbn;

    @Setter
    private int quantity;
}
