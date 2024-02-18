package com.tiagobarbosa.springbatchtests.processors;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassifierProcessorConfig {

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Bean
    public ItemProcessor classifierProcessor() {
        return new ClassifierCompositeItemProcessorBuilder<>()
                .classifier(classifier())
                .build();
    }

    @SuppressWarnings("rawtypes")
    private Classifier classifier() {
        return (Classifier<Object, ItemProcessor>) classifiable -> {
            if (classifiable instanceof Client)
                return new ClientProcessor();
            else
                return new TransactionProcessor();
        };
    }
}
