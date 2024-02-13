package com.tiagobarbosa.springbatchtests.readers;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ReadingFileDelimitedReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Client> readingFileDelimitedReader(@Value("#{jobParameters['clientsDelimitedFields']}") Resource clientsDelimitedFields) {
        return new FlatFileItemReaderBuilder<Client>()
                .name("readingFileDelimitedReader")
                .resource(clientsDelimitedFields)
                .delimited()
                .names("name", "surname", "age", "email")
                .targetType(Client.class)
                .build();
    }
}
