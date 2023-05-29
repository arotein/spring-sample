package com.example.demo.batch;

import com.example.demo.book.domain.entity.Book;
import com.example.demo.book.domain.entity.PublishingHouse;
import com.example.demo.book.domain.repository.PublishingHouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SampleJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PublishingHouseRepository repository;
    private final String name1 = "1번 출판사";
    private final String address1 = "1번 출판사 주소";
    private final String name2 = "2번 출판사";
    private final String address2 = "2번 출판사 주소";
    private int count = 0;

    @Bean
    public Job publishBookJob() {
        return jobBuilderFactory.get("publishBookJob")
                .start(firstStep_createPublishHouse())
                .next(secondStep_publishBook())
                .next(thirdStep_findBook())
                .build();
    }

    @Bean
    public Step firstStep_createPublishHouse() {
        return stepBuilderFactory.get("firstStep_createPublishHouse")
                .tasklet((contribution, chunkContext) -> {
                    repository.save(PublishingHouse.builder()
                            .name(name1)
                            .address(address1)
                            .build());

                    repository.save(PublishingHouse.builder()
                            .name(name2)
                            .address(address2)
                            .build());
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step secondStep_publishBook() {
        return stepBuilderFactory.get("secondStep_publishBook")
                .tasklet((contribution, chunkContext) -> {
                    if (count < 3) {
                        count++;
                        throw new RuntimeException("3보다 작으므로 실패"); // 재실행시 중단됐던 스텝부터 실핸되는거 확인함.
                    }

                    PublishingHouse house1 = repository.findByNameAndAddress(name1, address1);
                    house1.publishBook(Book.builder()
                            .isbn("isbn1")
                            .name("Batch Master")
                            .page(312)
                            .build());
                    return RepeatStatus.FINISHED;
                }).build();
    }
    // 트랜잭션은?

    @Bean
    public Step thirdStep_findBook() {
        return stepBuilderFactory.get("thirdStep_findBook")
                .tasklet((contribution, chunkContext) -> {
                    PublishingHouse house1 = repository.findByNameAndAddress(name1, address1);
                    List<Book> books = house1.getBooks();
                    log.info("books={}", books);
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
