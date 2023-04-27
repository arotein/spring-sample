package com.example.demo.mvc.domain.repository;

import com.example.demo.mvc.domain.entity.PublishingHouse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {
    @EntityGraph(attributePaths = "books")
    PublishingHouse findByBooks_Id(Long id);

    @EntityGraph(attributePaths = "books")
    PublishingHouse findByBooks_Isbn(String isbn);
}
