package com.example.demo.mvc.service;

import com.example.demo.mvc.dto.BookReqDto;
import com.example.demo.mvc.dto.BookResDto;

import java.util.List;

public interface BookService {
    BookResDto findBook(Long id);

    List<BookResDto> findAllBooks();

    boolean saveBook(BookReqDto dto);
}
