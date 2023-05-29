package com.example.demo.fixtureMonkey;

import com.example.demo.book.domain.repository.PublishingHouseRepository;
import com.example.demo.book.dto.BookReqDto;
import com.example.demo.book.service.BookServiceImpl;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.BuilderArbitraryIntrospector;
import com.navercorp.fixturemonkey.javax.validation.plugin.JavaxValidationPlugin;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FixtureMonkeyTest {
    @InjectMocks
    private BookServiceImpl service;
    @Spy
    private PublishingHouseRepository repository;
    private final FixtureMonkey monkey = FixtureMonkey.builder()
            .objectIntrospector(BuilderArbitraryIntrospector.INSTANCE)
            .plugin(new JavaxValidationPlugin())
            .defaultNotNull(true)
            .build();

    @RepeatedTest(10)
    void 객체_생성_테스트() {
        BookReqDto dto = monkey.giveMeBuilder(BookReqDto.class).sample();
        System.out.println(dto);
    }
}
