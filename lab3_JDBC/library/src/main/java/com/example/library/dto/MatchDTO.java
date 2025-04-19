package com.example.library.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for representing a Match.
 * Used for sending match data to the client.
 */
public class MatchDTO {
    /**
     * The unique identifier of the match.
     */
    private Long id;
    /**
     * The ID of the home team.
     */
    private Long homeTeamId;
    /**
     * The ID of the away team.
     */
    private Long awayTeamId;
    /**
     * The date and time when the match took place.
     */
    private LocalDateTime matchDate;
    /**
     * The score of the home team.
     */
    private Integer homeScore;
    /**
     * The score of the away team.
     */
    private Integer awayScore;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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