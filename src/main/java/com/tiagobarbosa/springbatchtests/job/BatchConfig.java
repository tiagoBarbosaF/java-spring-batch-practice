package com.tiagobarbosa.springbatchtests.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    private final JobRepository jobRepository;

    public BatchConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job job(Step imprimeHelloStep,
                   Step imprimeParImparStep,
                   Step readingFileFixedWidthStep,
                   Step readingFileDelimitedStep,
                   Step readingFileMultiFormatsStep,
                   Step readingMultipleFilesClientTransactionStep,
                   Step jdbcCursorStep,
                   Step jdbcPagingStep,
                   Step skipExceptionStep) {
        return new JobBuilder("PrincipalJob", jobRepository)
                .start(imprimeHelloStep)
                .next(imprimeParImparStep)
                .next(readingFileFixedWidthStep)
                .next(readingFileDelimitedStep)
                .next(readingFileMultiFormatsStep)
                .next(readingMultipleFilesClientTransactionStep)
                .next(jdbcCursorStep)
                .next(jdbcPagingStep)
                .next(skipExceptionStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
