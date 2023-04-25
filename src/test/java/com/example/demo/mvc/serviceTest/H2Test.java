package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.domain.entity.PublishingHouse;
import com.example.demo.mvc.domain.repository.PublishingHouseRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("h2")
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
        PublishingHouse savedPublishingHouse = repository.save(publishingHouse);

        assertThat(savedPublishingHouse.getAddress())
                .isEqualTo(address);
    }

    @Order(2)
    @Test
    void 출판사_조회() {
        Optional<PublishingHouse> opPublishingHouse = repository.findById(1L);

        assertThat(opPublishingHouse)
                .isNotEmpty();
        assertThat(opPublishingHouse.get().getAddress())
                .isEqualTo(address);
    }
}
