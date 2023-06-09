package com.example.demo.book.controllerTest;

import com.example.demo.exportToJson.controller.ExportObjectToJsonController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(ExportObjectToJsonController.class)
class JsonToTxtMvcTest {
    @MockBean
    private ExportObjectToJsonController controller;

//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private ExportObjectToJsonController controller;
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Nested
//    @DisplayName("내보내기 케이스들")
//    class ExportCases {
//        @Nested
//        @DisplayName("실패 케이스들")
//        class Fail {
//            @Test
//            @DisplayName("fileName 공백")
//            public void testObjectToJson() throws Exception {
//                // Given
//                ValidDto dto = ValidDto.builder()
//                        .fileName(" ")
//                        .build();
//
//                // When & Then
//                mockMvc.perform(post("/api/objectToJson")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(dto)))
//                        .andExpect(status().is4xxClientError())
//                        .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
//            }
//        }
//
//        @Nested
//        @DisplayName("성공 케이스들")
//        class Success {
//            @Test
//            @DisplayName("성공1")
//            public void case1() throws Exception {
//                // Given
//                ValidDto dto = ValidDto.builder()
//                        .fileName("myFile")
//                        .age(20)
//                        .type(Type.A_TYPE)
//                        .build();
//
//                // When & Then
//                mockMvc.perform(post("/api/objectToJson")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(dto)))
//                        .andExpect(status().isOk());
//            }
//        }
//    }
}
