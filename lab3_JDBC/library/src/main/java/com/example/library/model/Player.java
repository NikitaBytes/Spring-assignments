package com.example.library.model;

/**
 * Represents a Player entity in the application.
 * This class models the data stored in the database for a player.
 */
public class Player {
    /**
     * The unique identifier of the player.
     */
    private Long id;
    /**
     * The name of the player.
     */
    private String name;
    /**
     * The identifier of the team this player belongs to.
     */
    private Long teamId;

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

    public Long getTeamId() {
        return teamId;
    }
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}