package com.tiagobarbosa.springbatchtests.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PrintHelloStep {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public PrintHelloStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step imprimeHelloStep(Tasklet tasklet) {
        return new StepBuilder("FirstStep!!!", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}
