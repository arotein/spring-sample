package com.example.demo.mvc.controllerTest;

import com.example.demo.mvc.ValidDto;
import com.example.demo.mvc.controller.ExportObjectToJsonController;
import com.example.demo.mvc.service.ExportObjectToJsonServiceImpl;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("JsonToTxtController 테스트")
public class JsonToTxtControllerTest {
    @Mock
    private ExportObjectToJsonServiceImpl service;

    @InjectMocks
    private ExportObjectToJsonController controller;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("[성공]")
    void exportSuccess() throws Exception {
        // Given
        ValidDto dto = ValidDto.builder()
                .fileName("hello")
                .build();

        doReturn(Boolean.TRUE).when(service).fileGenerate(dto);

        // Then
        MvcResult result = mockMvc.perform(post("/api/objectToJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response).isEqualTo("true");
    }

    @Test
    @DisplayName("[실패] 파라미터 조건")
    void exportFail1() throws Exception {
        // given
        ValidDto dto = ValidDto.builder()
                .fileName(" ")
                .build();

        // when
        ResultActions actions = mockMvc.perform(post("/api/objectToJson")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // then
        actions
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andDo(print());
    }

    @Test
    @DisplayName("[실패] false응답")
    void exportFail2() throws Exception {
        // Given
        ValidDto dto = ValidDto.builder()
                .fileName("hello")
                .build();

        doReturn(Boolean.FALSE).when(service).fileGenerate(dto);

        // Then
        MvcResult result = mockMvc.perform(post("/api/objectToJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response).isEqualTo("false");
    }
}
