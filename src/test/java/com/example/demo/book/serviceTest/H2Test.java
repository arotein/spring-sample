package com.example.demo.book.serviceTest;

import com.example.demo.book.domain.entity.PublishingHouse;
import com.example.demo.book.domain.repository.PublishingHouseRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("h2")
@SpringBootTest
@Transactional
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class H2Test {
    @Autowired
    private PublishingHouseRepository repository;
    final String address = "주소";

    @Order(1)
    @Test
    void 출판사_저장() {
        PublishingHouse publishingHouse = PublishingHouse.builder()
                .name("A출판사")
                .address(address)
                .build();
        PublishingHouse savedPublishingHouse = repository.save(publishingHouse); // 왜 저장이 안될까?

        List<PublishingHouse> all = repository.findAll();
        System.out.println("all size = " + all.size());

        assertThat(savedPublishingHouse.getAddress())
                .isEqualTo(address);
    }

    @Order(2)
    @Test
    void 출판사_조회() {
        List<PublishingHouse> all = repository.findAll();
        System.out.println("all = " + all);

        Optional<PublishingHouse> opPublishingHouse = repository.findById(1L);

        assertThat(opPublishingHouse)
                .isNotEmpty();
        assertThat(opPublishingHouse.get().getAddress())
                .isEqualTo(address);
    }
}
