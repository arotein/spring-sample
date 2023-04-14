package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.ValidDto;
import com.example.demo.mvc.service.ExportObjectToJsonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("JsonToTxtService 테스트")
@ExtendWith(MockitoExtension.class)
public class JsonToTxtServiceTest {
    @InjectMocks
    private ExportObjectToJsonServiceImpl service;
    @TempDir
    public Path tempDir;

    private String fileName = "testFile";

    @BeforeEach
    public void init() {
        System.out.println("init service file path");
        ReflectionTestUtils.setField(service, "FILE_BASE_PATH", tempDir.toString());
    }

    @AfterEach
    public void deleteFile() throws IOException {
        System.out.println("delete file");
        Files.deleteIfExists(tempDir.resolve(fileName + ".json"));
    }

    @Test
    @DisplayName("[성공]")
    public void testObjectToJson() throws IOException {
        // Given
        ValidDto dto = ValidDto.builder()
                .fileName(fileName)
                .build();

        Path filePath = tempDir.resolve(dto.getFileName() + ".json");

        // Then
        boolean result = service.fileGenerate(dto);

        // Then
        assertThat(result).isTrue();
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            assertThat(inputStream).isNotEmpty();
            System.out.println(filePath);
        }
    }
}
