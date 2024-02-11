package com.tiagobarbosa.springbatchtests.writers;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadingFileFixedWidthWriterConfig {

    @Bean
    public ItemWriter<Client> readingFileFixedWidthWriter() {
        return item -> item.forEach(System.out::println);
    }
}
