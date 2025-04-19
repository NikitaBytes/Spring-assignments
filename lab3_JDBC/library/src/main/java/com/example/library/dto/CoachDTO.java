package com.example.library.dto;

/**
 * Data Transfer Object for representing a Coach.
 * Used for sending coach data to the client.
 */
public class CoachDTO {
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