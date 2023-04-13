package com.example.demo.jsonToTxt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Random;

@Data
@Builder
@ToString
@AllArgsConstructor
public class JsonObject {
    private Long templateId;
    private String contents;
    @JsonIgnore
    private List<String> preContents;

    public static JsonObject gen(Long id) {
        Random random = new Random();
        return JsonObject.builder()
                .templateId(id)
                .preContents(List.of("templateId" + id + "시작", Double.valueOf(random.nextDouble()).toString()))
                .build()
                .combineContents();
    }

    private JsonObject combineContents() {
        contents = String.join(" ", preContents);
        return this;
    }
}