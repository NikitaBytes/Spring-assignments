package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for creating or updating a Coach.
 * Contains the necessary information required for coach creation and update operations.
 */
public class CoachCreateUpdateDTO {

    /**
     * The name of the coach. Must not be blank.
     */
    @NotBlank(message = "Coach name must not be blank")
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}