package com.example.demo.mvc.dto;

import com.example.demo.mvc.domain.entity.Book;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReqDto {
    @NotBlank
    private String isbn;
    @NotBlank
    @Pattern(regexp = "[가-힣]{0,10}")
    private String name;
    @Min(1L)
    @Max(10L)
    @NotNull
    private Integer page;
    private LocalDate date;

    public Book toEntity() {
        return Book.builder()
                .isbn(isbn)
                .name(name)
                .page(page)
                .build();
    }
}
