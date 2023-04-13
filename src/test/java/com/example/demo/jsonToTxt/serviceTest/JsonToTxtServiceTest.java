package com.example.demo.jsonToTxt.serviceTest;

import com.example.demo.jsonToTxt.ValidDto;
import com.example.demo.jsonToTxt.service.ExportObjectToJsonServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("service 테스트")
@ExtendWith(MockitoExtension.class)
public class JsonToTxtServiceTest {
    @InjectMocks
    private ExportObjectToJsonServiceImpl service;

    @Test
    @DisplayName("성공")
    public void testObjectToJson() throws IOException {
        // Given
        ValidDto dto = ValidDto.builder()
                .fileName("test")
                .build();

        Path filePath = Paths.get("C:/Users/9cksq/OneDrive/바탕 화면", dto.getFileName() + ".json");

        // Then
        boolean result = service.fileGenerate(dto);

        // Then
        assertThat(result).isTrue();
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            assertThat(inputStream).isNotEmpty();
        } finally {
            Files.deleteIfExists(filePath);
        }
    }
}
