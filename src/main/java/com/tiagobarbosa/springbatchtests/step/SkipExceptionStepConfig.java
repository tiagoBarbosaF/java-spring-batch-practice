package com.tiagobarbosa.springbatchtests.step;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SkipExceptionStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public SkipExceptionStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step skipExceptionStep(ItemReader<Client> skipExceptionReader, ItemWriter<Client> readingFileWriter) {
        return new StepBuilder("skipExceptionStep", jobRepository)
                .<Client, Client>chunk(10, transactionManager)
                .reader(skipExceptionReader)
                .writer(readingFileWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(4)
                .build();
    }
}
