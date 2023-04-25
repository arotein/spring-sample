package com.example.demo.mvc.domainTest;

import com.example.demo.mvc.domain.entity.Book;
import com.example.demo.mvc.domain.entity.PublishingHouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class PublishingHouseTest {
    @Test
    void 책_등록() {
        // given
        PublishingHouse publishingHouse = PublishingHouse.builder().build();
        Book book = Book.builder().id(1L).build();
        Book book2 = Book.builder().id(2L).build();

        // when
        publishingHouse.publishBook(book);
        publishingHouse.publishBook(book2);

        // then
        assertThat(publishingHouse.getBooks()).contains(book2, book);
        assertThat(publishingHouse.getBooks()).contains(book, book2);
    }

    @Test
    void 책_존재여부() {
        // given
        PublishingHouse publishingHouse = PublishingHouse.builder().build();
        Book book = Book.builder().id(1L).build();
        Book book2 = Book.builder().id(2L).build();
        publishingHouse.publishBook(book);
        publishingHouse.publishBook(book2);

        // when
        boolean result1 = publishingHouse.existById(1L);
        boolean result2 = publishingHouse.existById(2L);
        boolean result3 = publishingHouse.existById(3L);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isFalse();
    }

    @Test
    void 책_페이지_수정() {
        // given
        PublishingHouse publishingHouse = PublishingHouse.builder().build();
        Book book = Book.builder().id(1L).page(50).build();
        publishingHouse.publishBook(book);

        // when
        publishingHouse.updateBookPage(1L, 100);

        // then
        assertThat(book.getPage()).isEqualTo(100);

        assertThatThrownBy(() -> publishingHouse.updateBookPage(2L, 301))
                .isInstanceOf(RuntimeException.class);
    }
}