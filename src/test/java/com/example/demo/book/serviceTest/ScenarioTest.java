package com.example.demo.book.serviceTest;

import com.example.demo.book.domain.entity.PublishingHouse;
import com.example.demo.book.domain.repository.PublishingHouseRepository;
import com.example.demo.book.dto.BookReqDto;
import com.example.demo.book.dto.BookResDto;
import com.example.demo.book.service.BookServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("h2")
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ScenarioTest {
    @Autowired
    private BookServiceImpl service;
    @SpyBean
    private PublishingHouseRepository repository;

    @BeforeEach
    void beforeEach() {
    }

    @Order(1)
    @Test
    void scenario1() {
        // given
        PublishingHouse publishingHouse1 = PublishingHouse.builder()
                .name("Hello출판사")
                .address("서울")
                .build();

        BookReqDto bookReqDto = new BookReqDto("isbn", "TDD", 540, null);

        PublishingHouse house = repository.save(publishingHouse1);

        // when
        service.publishBook(house.getId(), bookReqDto);

        BookResDto beforeBook = service.findBookByIsbn(bookReqDto.getIsbn());

        service.updateBookPage(beforeBook.getId(), 700);

        BookResDto afterBook = service.findBookById(beforeBook.getId());

        // then
        assertThat(afterBook.getPage()).isEqualTo(700);
    }
}
