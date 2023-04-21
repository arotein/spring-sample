package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.domain.entity.Book;
import com.example.demo.mvc.domain.entity.PublishingHouse;
import com.example.demo.mvc.domain.repository.PublishingHouseRepository;
import com.example.demo.mvc.dto.BookReqDto;
import com.example.demo.mvc.dto.BookResDto;
import com.example.demo.mvc.service.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookServiceImpl service;
    @Spy
    private PublishingHouseRepository repository;
    @Captor
    ArgumentCaptor<Long> longCaptor;
    private PublishingHouse publishingHouse1;
    private PublishingHouse publishingHouse2;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void beforeEach() {
        publishingHouse1 = Factory.publishingHouse(1L);
        publishingHouse2 = Factory.publishingHouse(2L);

        book1 = Factory.book(1L);
        book2 = Factory.book(2L);
        book3 = Factory.book(3L);

        publishingHouse1.publishBook(book1);
        publishingHouse1.publishBook(book2);
        publishingHouse2.publishBook(book3);
    }

    @Test
    void findBook() {
        // given
        when(repository.findByBooks_Id(1L)).thenReturn(publishingHouse1);
        when(repository.findByBooks_Id(2L)).thenReturn(publishingHouse1);
        when(repository.findByBooks_Id(3L)).thenReturn(publishingHouse2);

        // when
        BookResDto dto1 = service.findBook(1L);
        BookResDto dto2 = service.findBook(2L);
        BookResDto dto3 = service.findBook(3L);

        // then
        Assertions.assertThat(dto1.getId()).isEqualTo(1L);
        Assertions.assertThat(dto2.getId()).isEqualTo(2L);
        Assertions.assertThat(dto3.getId()).isEqualTo(3L);
    }

    @Test
    void findAllBooks_성공() {
        // given
        when(repository.findById(1L)).thenReturn(Optional.of(publishingHouse1));
        when(repository.findById(2L)).thenReturn(Optional.of(publishingHouse2));

        // when
        List<BookResDto> list1 = service.findAllBooks(1L);
        List<BookResDto> list2 = service.findAllBooks(2L);

        // then
        Assertions.assertThat(list1).hasSize(2);
        Assertions.assertThat(list2).hasSize(1);
    }

    @Test
    void findAllBooks_실패() {
        // when & then
        Assertions.assertThatThrownBy(() -> service.findAllBooks(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("출판사가 없습니다.");
    }

    @Test
    void publishBook() {
        // given
        BookReqDto book = BookReqDto.builder()
                .isbn("isbn")
                .name("TDD")
                .page(100)
                .build();
        PublishingHouse publishingHouse1 = Factory.publishingHouse(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(publishingHouse1));

        // when
        service.publishBook(1L, book);

        // then
        Assertions.assertThat(publishingHouse1.getBooks()).hasSize(1);
    }

    @Test
    void updateBookPage() {
        // given
        when(repository.findByBooks_Id(1L)).thenReturn(publishingHouse1);
        when(repository.findByBooks_Id(2L)).thenReturn(publishingHouse1);
        when(repository.findByBooks_Id(3L)).thenReturn(publishingHouse2);

        // when
        service.updateBookPage(1L, 100);
        service.updateBookPage(2L, 200);
        service.updateBookPage(3L, 300);
        service.updateBookPage(3L, 333);

        // then
        Assertions.assertThat(book1.getPage()).isEqualTo(100);
        Assertions.assertThat(book2.getPage()).isEqualTo(200);
        Assertions.assertThat(book3.getPage()).isEqualTo(333);

        Assertions.assertThat(book1.getVersion()).isEqualTo(2);
        Assertions.assertThat(book2.getVersion()).isEqualTo(2);
        Assertions.assertThat(book3.getVersion()).isEqualTo(3);
    }

    @Test
    void argumentCaptor_테스트() {
        // given
        when(repository.findByBooks_Id(1L)).thenReturn(publishingHouse1);
        when(repository.findByBooks_Id(2L)).thenReturn(publishingHouse1);

        // when
        service.updateBookPage(1L, 99);
        service.updateBookPage(2L, 201);

        // capture는 메서드 호출 이후에 사용가능, Mock만 가능
        verify(repository, times(2)).findByBooks_Id(longCaptor.capture());

        // then
        longCaptor.getAllValues();
        Assertions.assertThat(longCaptor.getAllValues()).contains(1L, 2L);
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
