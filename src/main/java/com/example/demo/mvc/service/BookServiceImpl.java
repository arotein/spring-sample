package com.example.demo.mvc.service;

import com.example.demo.aop.AutoAssignIndex;
import com.example.demo.mvc.domain.entity.Book;
import com.example.demo.mvc.domain.repository.PublishingHouseRepository;
import com.example.demo.mvc.dto.BookReqDto;
import com.example.demo.mvc.dto.BookResDto;
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
    public BookResDto findBook(Long bookId) {
        return BookResDto.of(findBookById(bookId));
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
        Book book = dto.toEntity();
        publishingHouseRepository.findById(publishingHouseId)
                .orElseThrow(() -> new RuntimeException("출판사가 없습니다."))
                .publishBook(book);
        return book.getId();
    }

    @Override
    public Long updateBookPage(Long bookId, Integer page) {
        Book book = findBookById(bookId);
        book.getPublishingHouse().updateBookPage(bookId, page);
        return book.getId();
    }

    private Book findBookById(Long bookId) {
        return publishingHouseRepository.findByBooks_Id(bookId)
                .getBooks()
                .stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("책이 없습니다."));
    }
}
