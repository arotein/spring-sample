package com.example.demo.jsonToTxt.controller;

import com.example.demo.jsonToTxt.service.ExportObjectToJsonService;
import com.example.demo.jsonToTxt.ValidDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Object를 Json파일로 내보내는 방법
 */

@Slf4j
@RequestMapping("/api/objectToJson")
@RestController
@RequiredArgsConstructor
public class ExportObjectToJsonController {
    private final ExportObjectToJsonService service;

    @PostMapping
    public ResponseEntity export(@Validated @RequestBody ValidDto dto) {
        log.info("export가 호출됨");
        boolean result = service.fileGenerate(dto);
        return ResponseEntity.ok(result);
    }
}
