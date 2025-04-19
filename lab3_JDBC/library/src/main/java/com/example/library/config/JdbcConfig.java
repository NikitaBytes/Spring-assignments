package com.example.library.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class for JDBC.
 * Configures the JdbcTemplate for database operations and enables transaction management.
 */
@EnableTransactionManagement
@Configuration
public class JdbcConfig {

    /**
     * Creates and configures a JdbcTemplate bean.
     * This bean provides a simplified way to interact with the database using JDBC.
     *
     * @param dataSource The DataSource bean automatically configured by Spring Boot,
     *                   used to establish database connections.
     * @return A configured JdbcTemplate instance ready for use in repositories.
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}