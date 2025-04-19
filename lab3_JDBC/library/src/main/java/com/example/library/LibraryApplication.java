package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Spring Boot Library application.
 * This class initializes and runs the Spring application context.
 */
@SpringBootApplication
public class LibraryApplication {
    /**
     * The main method which serves as the entry point for the Java application.
     * It delegates to Spring Boot's SpringApplication class to bootstrap the application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
}