package com.tiagobarbosa.springbatchtests.processors;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ValidationProcessorConfig {
    private final Set<String> emails = new HashSet<>();

    @Bean
    public ItemProcessor<Client, Client> validationProcessor() throws Exception {
        return new CompositeItemProcessorBuilder<Client, Client>()
                .delegates(beanValidatingProcessor(), emailValidatingProcessor())
                .build();
    }

    private ValidatingItemProcessor<Client> emailValidatingProcessor() {
        ValidatingItemProcessor<Client> validatingItemProcessor = new ValidatingItemProcessor<>();
        validatingItemProcessor.setValidator(validator());
        validatingItemProcessor.setFilter(true);
        return validatingItemProcessor;
    }

    private BeanValidatingItemProcessor<Client> beanValidatingProcessor() throws Exception {
        BeanValidatingItemProcessor<Client> validatingItemProcessor = new BeanValidatingItemProcessor<>();
        validatingItemProcessor.setFilter(true);
        validatingItemProcessor.afterPropertiesSet();
        return validatingItemProcessor;
    }

    private Validator<Client> validator() {
        return client -> {
            if (emails.contains(client.getEmail())) {
                throw new ValidationException(String.format("The customer %s has already been processed.", client.getEmail()));
            }
            emails.add(client.getEmail());
        };
    }
}
