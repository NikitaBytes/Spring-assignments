package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating or updating a Player.
 * Contains the necessary information required for player creation and update operations.
 */
public class PlayerCreateUpdateDTO {

    /**
     * The name of the player. Must not be blank.
     */
    @NotBlank(message = "Player name must not be blank")
    private String name;

    /**
     * The ID of the team the player belongs to. Must not be null.
     */
    @NotNull(message = "Team ID is required")
    private Long teamId;

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