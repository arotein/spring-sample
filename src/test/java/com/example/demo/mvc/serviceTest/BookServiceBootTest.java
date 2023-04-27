package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.domain.entity.Book;
import com.example.demo.mvc.domain.entity.PublishingHouse;
import com.example.demo.mvc.domain.repository.PublishingHouseRepository;
import com.example.demo.mvc.dto.BookResDto;
import com.example.demo.mvc.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("h2")
@SpringBootTest
class BookServiceBootTest {
    @Autowired
    private BookServiceImpl service;
    @SpyBean
    private PublishingHouseRepository repository;
    private PublishingHouse publishingHouse1;
    private Book book1;
    private Book book2;

    @BeforeEach
    void beforeEach() {
        publishingHouse1 = BookServiceMockTest.Factory.publishingHouse(1L);

        book1 = Factory.book(1L);
        book2 = Factory.book(2L);

        publishingHouse1.publishBook(book1);
        publishingHouse1.publishBook(book2);
    }

    @Test
    void AutoAssignIndex() {
        // given
        when(repository.findById(1L)).thenReturn(Optional.of(publishingHouse1));

        // when
        List<BookResDto> list = service.findAllBooks(1L);

        // then
        assertThat(list)
                .extracting("index")
                .containsExactly(0, 1);
    }

    static class Factory {
        public static PublishingHouse publishingHouse(Long id) {
            return PublishingHouse.builder().id(id).build();
        }

        public static Book book(Long id) {
            return Book.builder().id(id).build();
        }
    }
}
