package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.domain.entity.Book;
import com.example.demo.mvc.domain.entity.PublishingHouse;
import com.example.demo.mvc.service.BookServiceImpl;
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
