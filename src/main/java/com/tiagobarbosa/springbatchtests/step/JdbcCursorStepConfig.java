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
public class JdbcCursorStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public JdbcCursorStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step jdbcCursorStep(ItemReader<Client> jdbcCursorReader, ItemWriter<Client> readingFileWriter) {
        return new StepBuilder("jdbcCursorStep", jobRepository)
                .<Client, Client>chunk(5, transactionManager)
                .reader(jdbcCursorReader)
                .writer(readingFileWriter)
                .build();
    }
}
