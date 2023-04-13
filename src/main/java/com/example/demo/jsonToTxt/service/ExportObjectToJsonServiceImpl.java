package com.example.demo.jsonToTxt.service;

import com.example.demo.jsonToTxt.JsonObject;
import com.example.demo.jsonToTxt.ValidDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExportObjectToJsonServiceImpl implements ExportObjectToJsonService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public boolean fileGenerate(ValidDto dto) {
        log.info("fileGenerate 호출");
        List<JsonObject> li = generateObjects();

        // 내보낼 파일경로+파일명
        Path filePath = Paths.get("C:/Users/9cksq/OneDrive/바탕 화면", dto.getFileName() + ".json");

        // 객체를 string으로 변환
        String jsonStr = objectMapper.writeValueAsString(li);

        // 파일 내보내기
        Files.write(filePath, jsonStr.getBytes(StandardCharsets.UTF_8));

        return true;
    }

    private List<JsonObject> generateObjects() {
        List<JsonObject> li = new ArrayList<>();
        li.add(JsonObject.gen(1L));
        li.add(JsonObject.gen(2L));
        li.add(JsonObject.gen(3L));
        li.add(JsonObject.gen(4L));
        return li;
    }
}
