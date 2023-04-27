package com.example.demo.mvc.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer page;
    @Builder.Default
    @Column(nullable = false)
    private Integer version = 1;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false) // optional false : inner join
    @JoinColumn(name = "publishing_house_id", nullable = false)
    private PublishingHouse publishingHouse;

    void linkToPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    void updatePage(Integer page) {
        this.page = page;
        increaseVersion();
    }

    private void increaseVersion() {
        version++;
    }
}
