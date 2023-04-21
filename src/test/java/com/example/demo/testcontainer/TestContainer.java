package com.example.demo.testcontainer;

import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("testcontainers")
@Testcontainers
public abstract class TestContainer {
    private static final String MYSQL_IMAGE_VERSION = "mysql:latest";
    @Container
    public static final MySQLContainer mySQLContainer = new MySQLContainer(MYSQL_IMAGE_VERSION)
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("mysql");
}
