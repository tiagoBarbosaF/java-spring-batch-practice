package com.tiagobarbosa.springbatchtests.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MultipleFilesClientTransactionReaderConfig {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @StepScope
    @Bean
    public MultiResourceItemReader multipleFilesClientTransactionReader(
            @Value("#{jobParameters['multipleFilesClientTransaction']}") Resource[] multipleFilesClientTransaction,
            FlatFileItemReader readingFileMultiFormatsReader
    ) {
        return new MultiResourceItemReaderBuilder<>()
                .name("multipleFilesClientTransactionReader")
                .resources(multipleFilesClientTransaction)
                .delegate(new FileClientTransactionReader(readingFileMultiFormatsReader))
                .build();
    }
}
