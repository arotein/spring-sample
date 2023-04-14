package com.example.demo.mvc.controller;

import com.example.demo.mvc.dto.BookReqDto;
import com.example.demo.mvc.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequestMapping("/api/book")
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity findBook(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("book", bookService.findBook(id)));
    }

    @GetMapping
    public ResponseEntity findAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @PostMapping
    public ResponseEntity saveBook(@RequestBody @Validated BookReqDto dto) {
        return ResponseEntity.ok(bookService.saveBook(dto));
    }
}
