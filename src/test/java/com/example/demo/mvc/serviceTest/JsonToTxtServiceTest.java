package com.example.demo.mvc.serviceTest;

import com.example.demo.mvc.ValidDto;
import com.example.demo.mvc.domain.entity.Book;
import com.example.demo.mvc.domain.repository.PublishingHouseRepository;
import com.example.demo.mvc.service.BookServiceImpl;
import com.example.demo.mvc.service.ExportObjectToJsonServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JsonToTxtService 테스트")
@ExtendWith(MockitoExtension.class)
class JsonToTxtServiceTest {
    @InjectMocks
    private ExportObjectToJsonServiceImpl exportService;
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private PublishingHouseRepository bookRepository;
    @TempDir
    public Path tempDir;

    private String fileName = "testFile";

    @BeforeEach
    public void init() {
        System.out.println("init service file path");
        ReflectionTestUtils.setField(exportService, "FILE_BASE_PATH", tempDir.toString());
    }

    @AfterEach
    public void deleteFile() throws IOException {
        System.out.println("delete file");
        Files.deleteIfExists(tempDir.resolve(fileName + ".json"));
    }

    @Test
    @DisplayName("[성공] json export")
    public void testObjectToJson() throws IOException {
        // Given
        ValidDto dto = ValidDto.builder()
                .fileName(fileName)
                .build();

        Path filePath = tempDir.resolve(dto.getFileName() + ".json");

        // When
        boolean result = exportService.fileGenerate(dto);

        // Then
        Assertions.assertTrue(result);

        assertThat(result).isTrue();
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            assertThat(inputStream).isNotEmpty();
            System.out.println(filePath);
        }
    }

    @Test
    void 페이지_업데이트() {
    }

    @Test
    void 엔티티_비교() {
        Book book1 = Book.builder()
                .id(1L)
                .page(480)
                .build();
        Book book2 = Book.builder()
                .id(1L)
                .page(40)
                .build();

        assertThat(book1).isNotEqualTo(book2);
        assertThat(book1.getId()).isEqualTo(book2.getId());
    }

    @Test
    void 메서드_2번_호출_값설정() {

    }

    @Test
    void 리스트_정렬_테스트() {
        // given
        String a = "a";
        String b = "b";
        String c = "c";

        List<String> list = new ArrayList<>();
        list.add(a);
        list.add(c);
        list.add(b);

        // then
        MatcherAssert.assertThat(list, Matchers.containsInAnyOrder(List.of(a, b, c).toArray()));
        MatcherAssert.assertThat(list, Matchers.contains(List.of(a, c, b).toArray()));
    }
}
