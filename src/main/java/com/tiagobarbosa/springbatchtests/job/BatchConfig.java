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
    public Job job(Step imprimeHelloStep, Step imprimeParImparStep, Step readingFileFixedWidthStep) {
        return new JobBuilder("FirstJob!!", jobRepository)
                .start(imprimeHelloStep)
                .next(imprimeParImparStep)
                .next(readingFileFixedWidthStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
