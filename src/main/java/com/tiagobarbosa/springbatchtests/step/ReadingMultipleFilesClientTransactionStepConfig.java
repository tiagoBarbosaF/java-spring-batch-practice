package com.tiagobarbosa.springbatchtests.step;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ReadingMultipleFilesClientTransactionStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ReadingMultipleFilesClientTransactionStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public Step readingMultipleFilesClientTransactionStep(MultiResourceItemReader<Client> multipleFilesClientTransactionReader,
                                                          ItemWriter readingFileWriter) {
        return new StepBuilder("readingMultipleFilesClientTransactionStep", jobRepository)
                .chunk(1, transactionManager)
                .reader(multipleFilesClientTransactionReader)
                .writer(readingFileWriter)
                .build();

    }
}
