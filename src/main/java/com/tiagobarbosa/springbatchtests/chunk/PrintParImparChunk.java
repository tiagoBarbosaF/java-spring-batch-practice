package com.tiagobarbosa.springbatchtests.chunk;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PrintParImparChunk{
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public PrintParImparChunk(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step imprimeParImparStep() {
        return new StepBuilder("imprimeParImparStep", jobRepository)
                .<Integer, String>chunk(10, transactionManager)
                .reader(contaAteDezReader())
                .processor(parImparProcessor())
                .writer(imprimeWriter())
                .build();
    }

//    @Bean
    public IteratorItemReader<Integer> contaAteDezReader() {
        List<Integer> numerosAteDez = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numerosAteDez.add(i);
        }
        return new IteratorItemReader<Integer>(numerosAteDez.iterator());
    }

//    @Bean
    public FunctionItemProcessor<Integer, String> parImparProcessor() {
        return new FunctionItemProcessor<Integer, String>(item -> item % 2 == 0 ?
                String.format("Item %s é Par", item) :
                String.format("Item %s é Impar", item));
    }

//    @Bean
    public ItemWriter<String> imprimeWriter(){
        return itens -> itens.forEach(System.out::println);
    }
}
