package com.example.demo.s3.controller;

import com.example.demo.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/s3")
@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service service;
}
