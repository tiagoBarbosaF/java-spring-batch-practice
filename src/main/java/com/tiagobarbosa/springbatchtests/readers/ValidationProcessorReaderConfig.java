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
public class ValidationProcessorReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Client> validationProcessorReader(
            @Value("#{jobParameters['validationProcessor']}") Resource validationProcessor
    ) {
        return new FlatFileItemReaderBuilder<Client>()
                .name("validationProcessorReader")
                .resource(validationProcessor)
                .delimited()
                .names("name", "surname", "age", "email")
                .targetType(Client.class)
                .build();
    }
}
