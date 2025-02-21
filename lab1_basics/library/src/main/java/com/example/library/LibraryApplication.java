package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска Spring Boot приложения.
 * 
 * Аннотация @SpringBootApplication включает в себя:
 * - @Configuration (позволяет объявлять классы конфигурации Spring)
 * - @EnableAutoConfiguration (автоматически настраивает приложение на основе зависимостей)
 * - @ComponentScan (сканирует пакеты и находит компоненты Spring, такие как @Service, @Repository, @Controller)
 */
@SpringBootApplication
public class LibraryApplication {
    public static void main(String[] args) {
        // Запуск Spring Boot приложения
        SpringApplication.run(LibraryApplication.class, args);
    }
}