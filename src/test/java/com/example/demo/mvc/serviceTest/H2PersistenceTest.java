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

import java.util.List;

@DataJpaTest
@ActiveProfiles("h2")
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class H2PersistenceTest {
    @Autowired
    private PublishingHouseRepository repository;

    @Order(3)
    @Test
    void findAll() {
        // H2 DB는 전체 테스트 코드와 공유됨. 따라서 각 테스트 케이스간의 독립성은 보장할 수 없지만 시나리오 테스트는 가능.
        // 단, 다른 클래스간의 테스트 코드 실행순서는 보장할 수 없으니 유의
        List<PublishingHouse> result = repository.findAll();
        System.out.println(result);
    }
}
