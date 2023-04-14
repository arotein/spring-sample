package com.example.demo.mvc.controllerTest;

import com.example.demo.mvc.service.ExportObjectToJsonServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("SampleTest 테스트")
@WebMvcTest(ExportObjectToJsonServiceImpl.class)
public class SampleTest {
    @MockBean
    private ExportObjectToJsonServiceImpl service;

    @Test
    @DisplayName("서버기동 확인")
    void testServer() {

    }
}


