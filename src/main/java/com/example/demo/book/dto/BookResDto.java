package com.example.demo.book.dto;

import com.example.demo.book.domain.entity.Book;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResDto {
    private Integer index;
    private Long id;
    private String isbn;
    private String name;
    private Integer page;
    private LocalDateTime createdAt;

    public static BookResDto of(Book book) {
        return BookResDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .name(book.getName())
                .page(book.getPage())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
