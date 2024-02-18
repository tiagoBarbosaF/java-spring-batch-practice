package com.tiagobarbosa.springbatchtests.step;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ValidationProcessorStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ValidationProcessorStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step validationProcessorStep(
            ItemReader<Client> validationProcessorReader,
            ItemProcessor<Client, Client> validationProcessorProcessor,
            ItemWriter<Client> readingFileWriter
    ) {
        return new StepBuilder("validationProcessorStep", jobRepository)
                .<Client, Client>chunk(1, transactionManager)
                .reader(validationProcessorReader)
                .processor(validationProcessorProcessor)
                .writer(readingFileWriter)
                .build();
    }
}
