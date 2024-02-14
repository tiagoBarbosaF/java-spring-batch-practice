package com.tiagobarbosa.springbatchtests.readers;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcPagingReaderConfig {

    @Bean
    public JdbcPagingItemReader<Client> jdbcPagingReader(@Qualifier("appDataSource") DataSource dataSource,
                                                         PagingQueryProvider queryProvider) {
        return new JdbcPagingItemReaderBuilder<Client>()
                .name("jdbcPagingReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .pageSize(10)
                .rowMapper(new BeanPropertyRowMapper<>(Client.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appDataSource") DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("SELECT *");
        queryProvider.setFromClause("FROM client");
        queryProvider.setSortKey("email");

        return queryProvider;
    }
}
