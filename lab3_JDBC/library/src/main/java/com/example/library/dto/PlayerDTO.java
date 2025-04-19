package com.example.library.dto;

/**
 * Data Transfer Object for representing a Player.
 * Used for sending player data to the client.
 */
public class PlayerDTO {
    /**
     * The unique identifier of the player.
     */
    private Long id;
    /**
     * The name of the player.
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