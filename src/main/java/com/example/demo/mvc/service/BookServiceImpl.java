package com.example.demo.mvc.service;

import com.example.demo.mvc.domain.repository.BookRepository;
import com.example.demo.mvc.dto.BookReqDto;
import com.example.demo.mvc.dto.BookResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public BookResDto findBook(Long id) {
        return BookResDto.of(bookRepository.findById(id).orElseThrow(() -> new RuntimeException("책이 없습니다.")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookResDto> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResDto::of)
                .toList();
    }

    @Override
    public boolean saveBook(BookReqDto dto) {
        bookRepository.save(dto.to());
        return true;
    }
}
