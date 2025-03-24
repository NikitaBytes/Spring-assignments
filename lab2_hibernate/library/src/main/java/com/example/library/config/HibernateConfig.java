package com.example.library.config;

import javax.sql.DataSource;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
public class HibernateConfig {

    private final DataSource dataSource;

    // Конструктор для внедрения DataSource
    public HibernateConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Создает и настраивает LocalSessionFactoryBean для Hibernate.
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // Указываем пакеты, где расположены наши entity-классы
        sessionFactory.setPackagesToScan("com.example.library.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * Настройки Hibernate.
     * Важно явно прописать HBM2DDL_AUTO, иначе таблицы не будут созданы
     * (так как spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration).
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        // Для тестов удобно create-drop: схема создаётся заново при старте, удаляется при завершении
        properties.put(Environment.HBM2DDL_AUTO, "create-drop");
        return properties;
    }

    /**
     * Создает менеджер транзакций для Hibernate.
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}