package com.tiagobarbosa.springbatchtests.writers;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadingFileWriterConfig {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public ItemWriter readingFileWriter() {
        return item -> item.forEach(System.out::println);
    }
}
