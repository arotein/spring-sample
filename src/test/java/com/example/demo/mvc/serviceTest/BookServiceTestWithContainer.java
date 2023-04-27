package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.domain.entity.Book;
import com.example.demo.mvc.domain.entity.PublishingHouse;
import com.example.demo.mvc.domain.repository.PublishingHouseRepository;
import com.example.demo.mvc.dto.BookResDto;
import com.example.demo.mvc.service.BookServiceImpl;
import com.example.demo.testcontainer.TestContainer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceTestWithContainer extends TestContainer {
    @Autowired
    private BookServiceImpl service;
    @SpyBean
    private PublishingHouseRepository repository;

    @Order(1)
    @Test
    void PublishingHouseSave() {
        PublishingHouse publishingHouse = PublishingHouse.builder()
                .name("A출판사")
                .address("주소")
                .build();

        Book book = Book.builder()
                .isbn("123")
                .name("TDD Book")
                .page(123)
                .createdAt(LocalDateTime.now())
                .build();

        publishingHouse.publishBook(book);

        PublishingHouse save = repository.save(publishingHouse);
        System.out.println("publishingHouse = " + save.getId());
        System.out.println("book = " + book.getId());
    }

    @Order(2)
    @Test
    void 컨테이너_생성_테스트() {
        BookResDto bookDto = service.findBookById(1L);

        System.out.println(bookDto);

        Assertions.assertThat(bookDto.getId()).isEqualTo(1L);
    }
}
