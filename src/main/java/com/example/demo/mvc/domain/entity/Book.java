package com.example.demo.mvc.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false, unique = true)
    private Long uniqueNumber;
    private String name;
    private Integer page;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
