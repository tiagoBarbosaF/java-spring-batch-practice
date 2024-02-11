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
public class ReadingFileFixedWidthStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ReadingFileFixedWidthStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step readingFileFixedWidthStep(ItemReader<Client> readingFileFixedWidthReader, ItemWriter<Client> readingFileFixedWidthWriter){
        return new StepBuilder("readFileFixedWidthStep", jobRepository)
                .<Client, Client>chunk(1,transactionManager)
                .reader(readingFileFixedWidthReader)
                .writer(readingFileFixedWidthWriter)
                .build();
    }
}
