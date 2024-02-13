package com.tiagobarbosa.springbatchtests.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ReadingFileMultiFormatsReaderConfig {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @StepScope
    @Bean
    public FlatFileItemReader readingFileMultiFormatsReader(@Value("#{jobParameters['clientsMultiFormatFields']}") Resource clientsMultiFormatFields, LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder()
                .name("readingFileMultiFormatsReader")
                .resource(clientsMultiFormatFields)
                .lineMapper(lineMapper)
                .build();
    }
}
