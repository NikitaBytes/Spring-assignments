package com.example.library.dto;

/**
 * Data Transfer Object for representing a League.
 * Used for sending league data to the client.
 */
public class LeagueDTO {
    /**
     * The unique identifier of the league.
     */
    private Long id;
    /**
     * The name of the league.
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