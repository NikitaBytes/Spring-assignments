package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for creating or updating a League.
 * Contains the necessary information required for league creation and update operations.
 */
public class LeagueCreateUpdateDTO {

    /**
     * The name of the league. Must not be blank.
     */
    @NotBlank(message = "League name must not be blank")
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}