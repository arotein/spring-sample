package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.domain.entity.PublishingHouse;
import com.example.demo.mvc.domain.repository.PublishingHouseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("h2")
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class H2Test {
    @Autowired
    private PublishingHouseRepository repository;

    @Order(1)
    @Test
    void 출판사_저장() {
        PublishingHouse publishingHouse = PublishingHouse.builder()
                .name("A출판사")
                .address("주소")
                .build();
        PublishingHouse savedPublishingHouse = repository.save(publishingHouse);

        Assertions.assertThat(savedPublishingHouse.getAddress()).isEqualTo("주소");
    }

    @Order(2)
    @Test
    void 출판사_조회() {
        Optional<PublishingHouse> opPublishingHouse = repository.findById(1L);

        Assertions.assertThat(opPublishingHouse).isNotEmpty();
        Assertions.assertThat(opPublishingHouse.get().getAddress()).isEqualTo("주소");
    }
}
