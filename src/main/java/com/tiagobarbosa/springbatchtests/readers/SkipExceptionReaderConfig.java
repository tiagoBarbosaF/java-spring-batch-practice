package com.tiagobarbosa.springbatchtests.readers;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class SkipExceptionReaderConfig {

    @Bean
    public ItemReader<Client> skipExceptionReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Client>()
                .name("skipExceptionReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM client")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<Client> rowMapper() {
        return new RowMapper<>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.getRow() == 10 || rs.getRow() == 20 || rs.getRow() == 30 || rs.getRow() == 60) {
                    throw new SQLException(String.format("Finishing execution - Invalid client %s", rs.getString("email")));
                } else return clientRowMapper(rs);
            }

            private Client clientRowMapper(ResultSet rs) throws SQLException {
                return new Client(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        rs.getString("email"));
            }
        };
    }
}
