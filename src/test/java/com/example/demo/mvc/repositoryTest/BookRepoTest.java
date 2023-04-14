package com.example.demo.mvc.repositoryTest;

import com.example.demo.mvc.domain.entity.Book;
import com.example.demo.mvc.domain.repository.BookRepository;
import com.example.demo.mvc.dto.BookReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@DisplayName("book repo 테스트")
public class BookRepoTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("[성공] find one")
    void findOneSucess() {
        // given
        Long id = 1L;

        // when
        Optional<Book> book = bookRepository.findById(id);

        // then
        assertThat(book).isNotEmpty();
        assertThat(book.get().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("[실패] find one")
    void findOneFail() {
        // given
        Long id = 2L;

        // when
        Optional<Book> book = bookRepository.findById(id);

        // then
        assertThat(book).isEmpty();
    }

    @Test
    @DisplayName("[성공] find all")
    void findAll() {
        // when
        List<Book> books = bookRepository.findAll();

        // then
    }


    @Test
    @DisplayName("[성공] save book")
    void save() {
        // given
        Book book = new BookReqDto(3L, "TDD", 540).to();

        // when
        Book savedBook = bookRepository.save(book);

        // then
        assertThat(savedBook.getName()).isEqualTo("TDD");
        assertThat(savedBook.getUniqueNumber()).isEqualTo(3L);
        assertThat(savedBook.getId()).isNotNull();
    }
}