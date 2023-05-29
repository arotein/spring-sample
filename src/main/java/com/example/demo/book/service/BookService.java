package com.example.demo.book.service;

import com.example.demo.book.dto.BookReqDto;
import com.example.demo.book.dto.BookResDto;

import java.util.List;

public interface BookService {
    BookResDto findBookById(Long id);

    BookResDto findBookByIsbn(String isbn);

    List<BookResDto> findAllBooks(Long publishingHouseId);

    Long publishBook(Long publishingHouseId, BookReqDto dto);

    Long updateBookPage(Long id, Integer page);
}
