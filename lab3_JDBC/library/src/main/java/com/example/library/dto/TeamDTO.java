package com.example.library.dto;

import java.util.List;

/**
 * Data Transfer Object for representing a Team.
 * Used for sending team data to the client, including associated coach, league, players, and matches.
 */
public class TeamDTO {
    /**
     * The unique identifier of the team.
     */
    private Long id;
    /**
     * The name of the team.
     */
    private String name;
    /**
     * The coach associated with the team.
     */
    private CoachDTO coach;
    /**
     * The league the team belongs to.
     */
    private LeagueDTO league;
    /**
     * The list of players in the team.
     */
    private List<PlayerDTO> players;
    /**
     * The list of matches the team participated in.
     */
    private List<MatchDTO> matches;

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

    public CoachDTO getCoach() {
        return coach;
    }
    public void setCoach(CoachDTO coach) {
        this.coach = coach;
    }

    public LeagueDTO getLeague() {
        return league;
    }
    public void setLeague(LeagueDTO league) {
        this.league = league;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }
    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<MatchDTO> getMatches() {
        return matches;
    }
    public void setMatches(List<MatchDTO> matches) {
        this.matches = matches;
    }
}