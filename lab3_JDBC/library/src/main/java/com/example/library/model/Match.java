package com.example.library.model;

import java.time.LocalDateTime;

/**
 * Represents a Match entity in the application.
 * This class models the data stored in the database for a match between two teams.
 */
public class Match {
    /**
     * The unique identifier of the match.
     */
    private Long id;
    /**
     * The identifier of the home team participating in the match.
     */
    private Long homeTeamId;
    /**
     * The identifier of the away team participating in the match.
     */
    private Long awayTeamId;
    /**
     * The date and time when the match took place.
     */
    private LocalDateTime matchDate;
    /**
     * The score achieved by the home team.
     */
    private Integer homeScore;
    /**
     * The score achieved by the away team.
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