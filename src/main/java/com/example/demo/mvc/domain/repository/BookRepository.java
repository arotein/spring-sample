package com.example.demo.mvc.domain.repository;

import com.example.demo.mvc.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
