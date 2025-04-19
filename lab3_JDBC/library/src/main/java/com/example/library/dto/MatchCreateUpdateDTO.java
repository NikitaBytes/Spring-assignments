package com.example.library.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating or updating a Match.
 * Contains the necessary information required for match creation and update operations.
 */
public class MatchCreateUpdateDTO {

    /**
     * The ID of the home team. Must not be null.
     */
    @NotNull(message = "Home team ID is required")
    private Long homeTeamId;

    /**
     * The ID of the away team. Must not be null.
     */
    @NotNull(message = "Away team ID is required")
    private Long awayTeamId;

    /**
     * The date and time of the match. Must not be null.
     */
    @NotNull(message = "Match date is required")
    private LocalDateTime matchDate;

    /**
     * The score of the home team. Must not be null.
     */
    @NotNull(message = "Home score is required")
    private Integer homeScore;

    /**
     * The score of the away team. Must not be null.
     */
    @NotNull(message = "Away score is required")
    private Integer awayScore;

    public Long getHomeTeamId() {
        return homeTeamId;
    }
    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }
    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }
    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public Integer getHomeScore() {
        return homeScore;
    }
    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }
    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }
}