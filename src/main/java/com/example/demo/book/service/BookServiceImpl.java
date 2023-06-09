package com.example.demo.book.service;

import com.example.demo.aop.AutoAssignIndex;
import com.example.demo.book.domain.entity.Book;
import com.example.demo.book.domain.repository.PublishingHouseRepository;
import com.example.demo.book.dto.BookReqDto;
import com.example.demo.book.dto.BookResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final PublishingHouseRepository publishingHouseRepository;

    @Transactional(readOnly = true)
    @Override
    public BookResDto findBookById(Long bookId) {
        return BookResDto.of(findByBookId(bookId));
    }

    @Transactional(readOnly = true)
    @Override
    public BookResDto findBookByIsbn(String isbn) {
        return BookResDto.of(findByIsbn(isbn));
    }

    @AutoAssignIndex
    @Transactional(readOnly = true)
    @Override
    public List<BookResDto> findAllBooks(Long publishingHouseId) {
        return publishingHouseRepository.findById(publishingHouseId)
                .orElseThrow(() -> new RuntimeException("출판사가 없습니다."))
                .getBooks()
                .stream()
                .map(BookResDto::of)
                .toList();
    }

    @Override
    public Long publishBook(Long publishingHouseId, BookReqDto dto) {
        publishingHouseRepository.findById(publishingHouseId)
                .orElseThrow(() -> new RuntimeException("출판사가 없습니다."))
                .publishBook(dto.toEntity());
        return publishingHouseId;
    }

    @Override
    public Long updateBookPage(Long bookId, Integer page) {
        Book book = findByBookId(bookId);
        book.getPublishingHouse().updateBookPage(bookId, page);
        return book.getId();
    }

    private Book findByBookId(Long bookId) {
        return publishingHouseRepository.findByBooks_Id(bookId)
                .getBooks()
                .stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("책이 없습니다."));
    }

    private Book findByIsbn(String isbn) {
        return publishingHouseRepository.findByBooks_Isbn(isbn)
                .getBooks()
                .stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("책이 없습니다."));
    }
}
