package com.example.library.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating or updating a Team.
 * Contains the necessary information required for team creation and update operations.
 */
public class TeamCreateUpdateDTO {

    /**
     * The name of the team. Must not be blank.
     */
    @NotBlank(message = "Team name must not be blank")
    private String name;

    /**
     * The ID of the coach associated with the team. Must not be null.
     */
    @NotNull(message = "Coach ID is required")
    private Long coachId;

    /**
     * The ID of the league the team belongs to. Must not be null.
     */
    @NotNull(message = "League ID is required")
    private Long leagueId;

    /**
     * A list of player IDs to associate with the team. Optional.
     * Allows creating a team without players initially.
     */
    // ⚠️ Сделано НЕ обязательным: можно создать команду без игроков, а потом привязать их
    private List<Long> playerIds;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getCoachId() {
        return coachId;
    }
    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Long getLeagueId() {
        return leagueId;
    }
    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public List<Long> getPlayerIds() {
        return playerIds;
    }
    public void setPlayerIds(List<Long> playerIds) {
        this.playerIds = playerIds;
    }
}