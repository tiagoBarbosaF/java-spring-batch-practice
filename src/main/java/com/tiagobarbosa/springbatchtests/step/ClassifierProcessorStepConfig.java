package com.tiagobarbosa.springbatchtests.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ClassifierProcessorStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ClassifierProcessorStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Bean
    public Step classifierProcessorStep(
            FlatFileItemReader classifierProcessorReader,
            ItemProcessor classifierProcessor,
            ItemWriter readingFileWriter
    ) {
        return new StepBuilder("classifierProcessorStep", jobRepository)
                .chunk(1, transactionManager)
                .reader(classifierProcessorReader)
                .processor(classifierProcessor)
                .writer(readingFileWriter)
                .build();
    }
}
