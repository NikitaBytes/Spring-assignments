package com.example.library.model;

/**
 * Represents a Team entity in the application.
 * This class models the data stored in the database for a team.
 */
public class Team {
    /**
     * The unique identifier of the team.
     */
    private Long id;
    /**
     * The name of the team.
     */
    private String name;
    /**
     * The identifier of the coach associated with this team.
     */
    private Long coachId;
    /**
     * The identifier of the league this team belongs to.
     */
    private Long leagueId;

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
}