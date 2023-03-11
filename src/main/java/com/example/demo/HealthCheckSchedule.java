package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HealthCheckSchedule {
    @Scheduled(cron = "0/5 * * * * *")
    public void healthScheduled() {
        log.info("@@ HealthCheck @@");
    }
}
