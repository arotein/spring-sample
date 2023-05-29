package com.example.demo.s3.controller;

import com.example.demo.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequestMapping("/api/s3")
@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service service;

    @PostMapping("/img")
    public String imgUpload(@RequestPart MultipartFile file) {
        service.upload(file);
        return "성공";
    }

    @GetMapping("/img/{key}")
    public String imgGet(@PathVariable String key) {
        service.get(key);
        return "성공";
    }
}
