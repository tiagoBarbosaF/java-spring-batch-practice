package com.tiagobarbosa.springbatchtests.step;

import com.tiagobarbosa.springbatchtests.readers.FileClientTransactionReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ReadingFileMultiFormatsStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ReadingFileMultiFormatsStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public Step readingFileMultiFormatsStep(FlatFileItemReader readingFileMultiFormatsReader, ItemWriter readingFileWriter) {
        return new StepBuilder("readingFileMultiFormatsStep", jobRepository)
                .chunk(1, transactionManager)
                .reader(new FileClientTransactionReader(readingFileMultiFormatsReader))
                .writer(readingFileWriter)
                .build();
    }
}
