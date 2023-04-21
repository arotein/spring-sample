package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.domain.entity.PublishingHouse;
import com.example.demo.mvc.domain.repository.PublishingHouseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("local")
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MySQLTest {
    @Autowired
    private PublishingHouseRepository repository;

    @Order(1)
    @Test
    void 출판사_저장() {
        // given
        PublishingHouse publishingHouse = PublishingHouse.builder()
                .name("A출판사")
                .address("주소")
                .build();

        // when
        PublishingHouse savedPublishingHouse = repository.save(publishingHouse);

        // then
        Assertions.assertThat(savedPublishingHouse.getAddress()).isEqualTo("주소");
    }

    @Order(2)
    @Test
    void 출판사_조회() {
    }
}
