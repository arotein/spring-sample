package com.example.demo.mvc.dto;

import com.example.demo.mvc.domain.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResDto {
    private Long id;
    private Long uniqueNumber;
    private String name;
    private Integer page;
    private LocalDateTime createdAt;

    public static BookResDto of(Book book) {
        return BookResDto.builder()
                .id(book.getId())
                .uniqueNumber(book.getUniqueNumber())
                .name(book.getName())
                .page(book.getPage())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
