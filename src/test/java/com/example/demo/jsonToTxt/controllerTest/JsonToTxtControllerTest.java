package com.example.demo.jsonToTxt.controllerTest;

import com.example.demo.jsonToTxt.ValidDto;
import com.example.demo.jsonToTxt.controller.ExportObjectToJsonController;
import com.example.demo.jsonToTxt.service.ExportObjectToJsonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("JsonToTxt Controller 테스트")
@ExtendWith(MockitoExtension.class)
public class JsonToTxtControllerTest {
    @Mock
    private ExportObjectToJsonServiceImpl service;

    @InjectMocks
    private ExportObjectToJsonController controller;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("컨트롤러 실패 테스트")
    void exportFail() throws Exception {
        // Given
        ValidDto dto = ValidDto.builder()
                .fileName(" ")
                .build();

        // Then
        mockMvc.perform(post("/api/objectToJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    @DisplayName("컨트롤러 성공 테스트")
    void exportSuccess() throws Exception {
        // Given
        ValidDto dto = ValidDto.builder()
                .fileName("hello")
                .build();

        doReturn(true).when(service).fileGenerate(dto);

        // Then
        MvcResult result = mockMvc.perform(post("/api/objectToJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response).isEqualTo("true");
    }
}
