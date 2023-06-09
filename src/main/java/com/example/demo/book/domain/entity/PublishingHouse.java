package com.example.demo.book.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "books")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PublishingHouse { // 출판사
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "publishingHouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public void publishBook(Book book) {
        books.add(book);
        book.linkToPublishingHouse(this);
    }

    public boolean existById(Long bookId) {
        return books.stream()
                .anyMatch(book -> book.getId().equals(bookId));
    }

    public void updateBookPage(Long bookId, Integer page) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("bookId가 존재하지 않음. bookId = " + bookId));

        book.updatePage(page);
    }
}
