package com.example.demo.mvc.dto;

import com.example.demo.mvc.domain.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReqDto {
    @Min(1L)
    private Long uniqueNumber;
    @NotBlank
    private String name;
    @Min(1L)
    private Integer page;

    public Book to() {
        return Book.builder()
                .uniqueNumber(uniqueNumber)
                .name(name)
                .page(page)
                .build();
    }
}
