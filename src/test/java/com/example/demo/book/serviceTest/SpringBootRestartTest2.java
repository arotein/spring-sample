package com.example.demo.book.serviceTest;

import com.example.demo.book.domain.entity.Book;
import com.example.demo.book.domain.entity.PublishingHouse;
import com.example.demo.book.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootRestartTest2 {
    @Autowired
    private BookServiceImpl service;
    private PublishingHouse publishingHouse1;
    private Book book1;
    private Book book2;

    @Test
    void test() {
    }
}
