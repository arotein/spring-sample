package com.example.demo.testConfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@TestConfiguration
@EnableJpaAuditing
public class TestConfig {
    @Bean
    public DateTimeProvider dateTimeProvider() {
        return CurrentDateTimeProvider.INSTANCE;
    }
}
