package com.example.library.model;

/**
 * Represents a Coach entity in the application.
 * This class models the data stored in the database for a coach.
 */
public class Coach {
    /**
     * The unique identifier of the coach.
     */
    private Long id;
    /**
     * The name of the coach.
     */
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}