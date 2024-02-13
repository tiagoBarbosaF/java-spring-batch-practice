package com.tiagobarbosa.springbatchtests.readers;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ReadingFileFixedWidthReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Client> readingFileFixedWidthReader(@Value("#{jobParameters['clientsFile']}") Resource clientsFile) {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43));
        DefaultLineMapper<Client> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet -> new Client(
                fieldSet.readString(0).trim(),
                fieldSet.readString(1).trim(),
                fieldSet.readInt(2),
                fieldSet.readString(3).trim()
        ));

        return new FlatFileItemReaderBuilder<Client>()
                .name("readingFileFixedWidthReader")
                .resource(clientsFile)
                .lineMapper(lineMapper)
                .build();
    }
}
