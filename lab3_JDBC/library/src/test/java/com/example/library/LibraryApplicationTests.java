package com.example.library;

import com.example.library.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Integration tests for the Library application.
 * Tests the full application context loading and REST API endpoints for CRUD operations.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryApplicationTests {
    /**
     * TestRestTemplate used to make HTTP requests to the running application during tests.
     */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Basic test to ensure the Spring application context loads successfully.
     */
    @Test
    void contextLoads() {
        // Basic test to verify application context loads
    }

    // === League Tests ===
    /**
     * Tests the full CRUD lifecycle for League entities via the REST API.
     * Covers creation, retrieval (single and all), update, and deletion of leagues.
     */
    @Test
    void leaguesCrudViaRest() {
        // Create new league
        LeagueCreateUpdateDTO createDTO = new LeagueCreateUpdateDTO();
        createDTO.setName("Test League");
        ResponseEntity<LeagueDTO> createResponse = restTemplate.postForEntity(
                "/api/leagues", createDTO, LeagueDTO.class);
        
        Assertions.assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        LeagueDTO created = createResponse.getBody();
        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("Test League", created.getName());
        
        // Get league
        ResponseEntity<LeagueDTO> getResponse = restTemplate.getForEntity(
                "/api/leagues/{id}", LeagueDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        LeagueDTO getResponseBody = getResponse.getBody();
        Assertions.assertNotNull(getResponseBody);
        Assertions.assertEquals(created.getId(), getResponseBody.getId());
        
        // Update league
        LeagueCreateUpdateDTO updateDTO = new LeagueCreateUpdateDTO();
        updateDTO.setName("Updated League");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LeagueCreateUpdateDTO> requestEntity = new HttpEntity<>(updateDTO, headers);
        
        ResponseEntity<LeagueDTO> updateResponse = restTemplate.exchange(
                "/api/leagues/{id}", HttpMethod.PUT, requestEntity, LeagueDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        LeagueDTO updateResponseBody = updateResponse.getBody();
        Assertions.assertNotNull(updateResponseBody);
        Assertions.assertEquals("Updated League", updateResponseBody.getName());
        
        // Get all leagues
        ResponseEntity<List<LeagueDTO>> getAllResponse = restTemplate.exchange(
                "/api/leagues", HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<LeagueDTO>>() {});
        
        Assertions.assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());
        List<LeagueDTO> leagues = getAllResponse.getBody();
        Assertions.assertNotNull(leagues);
        Assertions.assertTrue(!leagues.isEmpty());
        
        // Delete league
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                "/api/leagues/{id}", HttpMethod.DELETE, null, Void.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
        
        // Verify it's deleted
        ResponseEntity<LeagueDTO> verifyDeleteResponse = restTemplate.getForEntity(
                "/api/leagues/{id}", LeagueDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.NOT_FOUND, verifyDeleteResponse.getStatusCode());
    }
    
    // === Coach Tests ===
    /**
     * Tests the full CRUD lifecycle for Coach entities via the REST API.
     * Covers creation, retrieval, update, and deletion of coaches.
     */
    @Test
    void coachesCrudViaRest() {
        // Create new coach
        CoachCreateUpdateDTO createDTO = new CoachCreateUpdateDTO();
        createDTO.setName("Test Coach");
        ResponseEntity<CoachDTO> createResponse = restTemplate.postForEntity(
                "/api/coaches", createDTO, CoachDTO.class);
        
        Assertions.assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        CoachDTO created = createResponse.getBody();
        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("Test Coach", created.getName());
        
        // Get coach
        ResponseEntity<CoachDTO> getResponse = restTemplate.getForEntity(
                "/api/coaches/{id}", CoachDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        CoachDTO getResponseBody = getResponse.getBody();
        Assertions.assertNotNull(getResponseBody);
        Assertions.assertEquals(created.getId(), getResponseBody.getId());
        
        // Update coach
        CoachCreateUpdateDTO updateDTO = new CoachCreateUpdateDTO();
        updateDTO.setName("Updated Coach");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CoachCreateUpdateDTO> requestEntity = new HttpEntity<>(updateDTO, headers);
        
        ResponseEntity<CoachDTO> updateResponse = restTemplate.exchange(
                "/api/coaches/{id}", HttpMethod.PUT, requestEntity, CoachDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        CoachDTO updateResponseBody = updateResponse.getBody();
        Assertions.assertNotNull(updateResponseBody);
        Assertions.assertEquals("Updated Coach", updateResponseBody.getName());
        
        // Delete coach
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                "/api/coaches/{id}", HttpMethod.DELETE, null, Void.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
    }
    
    // === Player Tests ===
    /**
     * Tests the full CRUD lifecycle for Player entities via the REST API.
     * Requires creating a temporary team first.
     * Covers creation, retrieval, update, and deletion of players.
     */
    @Test
    void playersCrudViaRest() {
        // First create a team for the player to belong to
        Long teamId = createTeamForTesting();
        
        // Create new player
        PlayerCreateUpdateDTO createDTO = new PlayerCreateUpdateDTO();
        createDTO.setName("Test Player");
        createDTO.setTeamId(teamId);
        
        ResponseEntity<PlayerDTO> createResponse = restTemplate.postForEntity(
                "/api/players", createDTO, PlayerDTO.class);
        
        Assertions.assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        PlayerDTO created = createResponse.getBody();
        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("Test Player", created.getName());
        
        // Get player
        ResponseEntity<PlayerDTO> getResponse = restTemplate.getForEntity(
                "/api/players/{id}", PlayerDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        PlayerDTO getResponseBody = getResponse.getBody();
        Assertions.assertNotNull(getResponseBody);
        Assertions.assertEquals(created.getId(), getResponseBody.getId());
        
        // Update player
        PlayerCreateUpdateDTO updateDTO = new PlayerCreateUpdateDTO();
        updateDTO.setName("Updated Player");
        updateDTO.setTeamId(teamId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlayerCreateUpdateDTO> requestEntity = new HttpEntity<>(updateDTO, headers);
        
        ResponseEntity<PlayerDTO> updateResponse = restTemplate.exchange(
                "/api/players/{id}", HttpMethod.PUT, requestEntity, PlayerDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        PlayerDTO updateResponseBody = updateResponse.getBody();
        Assertions.assertNotNull(updateResponseBody);
        Assertions.assertEquals("Updated Player", updateResponseBody.getName());
        
        // Delete player
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                "/api/players/{id}", HttpMethod.DELETE, null, Void.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
    }
    
    // === Match Tests ===
    /**
     * Tests the full CRUD lifecycle for Match entities via the REST API.
     * Requires creating temporary teams first.
     * Covers creation, retrieval, update, and deletion of matches.
     */
    @Test
    void testMatchRepository() {
        // Create two teams for a match
        Long homeTeamId = createTeamForTesting();
        Long awayTeamId = createTeamForTesting();
        
        // Create new match
        MatchCreateUpdateDTO createDTO = new MatchCreateUpdateDTO();
        createDTO.setHomeTeamId(homeTeamId);
        createDTO.setAwayTeamId(awayTeamId);
        createDTO.setMatchDate(LocalDateTime.now());
        createDTO.setHomeScore(2);
        createDTO.setAwayScore(1);
        
        ResponseEntity<MatchDTO> createResponse = restTemplate.postForEntity(
                "/api/matches", createDTO, MatchDTO.class);
        
        Assertions.assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        MatchDTO created = createResponse.getBody();
        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(homeTeamId, created.getHomeTeamId());
        Assertions.assertEquals(awayTeamId, created.getAwayTeamId());
        Assertions.assertEquals(Integer.valueOf(2), created.getHomeScore());
        Assertions.assertEquals(Integer.valueOf(1), created.getAwayScore());
        
        // Get match
        ResponseEntity<MatchDTO> getResponse = restTemplate.getForEntity(
                "/api/matches/{id}", MatchDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        MatchDTO getResponseBody = getResponse.getBody();
        Assertions.assertNotNull(getResponseBody);
        Assertions.assertEquals(created.getId(), getResponseBody.getId());
        
        // Update match score
        MatchCreateUpdateDTO updateDTO = new MatchCreateUpdateDTO();
        updateDTO.setHomeTeamId(homeTeamId);
        updateDTO.setAwayTeamId(awayTeamId);
        updateDTO.setMatchDate(created.getMatchDate());
        updateDTO.setHomeScore(3);  // Updated score
        updateDTO.setAwayScore(2);  // Updated score
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MatchCreateUpdateDTO> requestEntity = new HttpEntity<>(updateDTO, headers);
        
        ResponseEntity<MatchDTO> updateResponse = restTemplate.exchange(
                "/api/matches/{id}", HttpMethod.PUT, requestEntity, MatchDTO.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        MatchDTO updateResponseBody = updateResponse.getBody();
        Assertions.assertNotNull(updateResponseBody);
        Assertions.assertEquals(Integer.valueOf(3), updateResponseBody.getHomeScore());
        Assertions.assertEquals(Integer.valueOf(2), updateResponseBody.getAwayScore());
        
        // Delete match
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                "/api/matches/{id}", HttpMethod.DELETE, null, Void.class, created.getId());
        
        Assertions.assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
    }
    
    // === Complex Workflow Test ===
    /**
     * Tests a complex workflow involving multiple entities (League, Coach, Team, Player, Match).
     * Simulates creating related entities, associating them, retrieving details with associations,
     * and finally cleaning up the created data.
     */
    @Test
    void fullTeamPlayerMatchFlow() {
        // 1. Create a league
        LeagueCreateUpdateDTO leagueDTO = new LeagueCreateUpdateDTO();
        leagueDTO.setName("Premier League");
        ResponseEntity<LeagueDTO> leagueResponse = restTemplate.postForEntity("/api/leagues", leagueDTO, LeagueDTO.class);
        LeagueDTO league = leagueResponse.getBody();
        Assertions.assertNotNull(league);
        
        // 2. Create a coach
        CoachCreateUpdateDTO coachDTO = new CoachCreateUpdateDTO();
        coachDTO.setName("Jurgen Klopp");
        ResponseEntity<CoachDTO> coachResponse = restTemplate.postForEntity("/api/coaches", coachDTO, CoachDTO.class);
        CoachDTO coach = coachResponse.getBody();
        Assertions.assertNotNull(coach);
        
        // 3. Create two teams
        TeamCreateUpdateDTO team1DTO = new TeamCreateUpdateDTO();
        team1DTO.setName("Liverpool");
        team1DTO.setCoachId(coach.getId());
        team1DTO.setLeagueId(league.getId());
        team1DTO.setPlayerIds(Collections.emptyList()); // Initially no players
        ResponseEntity<TeamDTO> team1Response = restTemplate.postForEntity("/api/teams", team1DTO, TeamDTO.class);
        TeamDTO team1 = team1Response.getBody();
        Assertions.assertNotNull(team1);
        
        TeamCreateUpdateDTO team2DTO = new TeamCreateUpdateDTO();
        team2DTO.setName("Manchester City");
        team2DTO.setCoachId(coach.getId());
        team2DTO.setLeagueId(league.getId());
        team2DTO.setPlayerIds(Collections.emptyList()); // Initially no players
        ResponseEntity<TeamDTO> team2Response = restTemplate.postForEntity("/api/teams", team2DTO, TeamDTO.class);
        TeamDTO team2 = team2Response.getBody();
        Assertions.assertNotNull(team2);
        
        // 4. Create some players for each team
        PlayerCreateUpdateDTO player1DTO = new PlayerCreateUpdateDTO();
        player1DTO.setName("Mohamed Salah");
        player1DTO.setTeamId(team1.getId());
        ResponseEntity<PlayerDTO> player1Response = restTemplate.postForEntity("/api/players", player1DTO, PlayerDTO.class);
        PlayerDTO player1 = player1Response.getBody();
        Assertions.assertNotNull(player1);
        
        PlayerCreateUpdateDTO player2DTO = new PlayerCreateUpdateDTO();
        player2DTO.setName("Kevin De Bruyne");
        player2DTO.setTeamId(team2.getId());
        ResponseEntity<PlayerDTO> player2Response = restTemplate.postForEntity("/api/players", player2DTO, PlayerDTO.class);
        PlayerDTO player2 = player2Response.getBody();
        Assertions.assertNotNull(player2);
        
        // 5. Create a match between the teams
        MatchCreateUpdateDTO matchDTO = new MatchCreateUpdateDTO();
        matchDTO.setHomeTeamId(team1.getId());
        matchDTO.setAwayTeamId(team2.getId());
        matchDTO.setMatchDate(LocalDateTime.now());
        matchDTO.setHomeScore(2);
        matchDTO.setAwayScore(1);
        ResponseEntity<MatchDTO> matchResponse = restTemplate.postForEntity("/api/matches", matchDTO, MatchDTO.class);
        MatchDTO match = matchResponse.getBody();
        Assertions.assertNotNull(match);
        
        // 6. Verify team details with associated entities
        ResponseEntity<TeamDTO> teamResponse = restTemplate.getForEntity("/api/teams/{id}", 
                TeamDTO.class, team1.getId());
        
        Assertions.assertEquals(HttpStatus.OK, teamResponse.getStatusCode());
        TeamDTO retrievedTeam = teamResponse.getBody();
        Assertions.assertNotNull(retrievedTeam);
        Assertions.assertNotNull(retrievedTeam.getCoach());
        
        // Verify coach association
        Assertions.assertEquals(coach.getId(), retrievedTeam.getCoach().getId());
        Assertions.assertEquals(coach.getName(), retrievedTeam.getCoach().getName());
        
        // Verify league association
        Assertions.assertNotNull(retrievedTeam.getLeague());
        Assertions.assertEquals(league.getId(), retrievedTeam.getLeague().getId());
        Assertions.assertEquals(league.getName(), retrievedTeam.getLeague().getName());
        
        // Verify player association
        Assertions.assertNotNull(retrievedTeam.getPlayers());
        boolean foundPlayer = false;
        for (PlayerDTO p : retrievedTeam.getPlayers()) {
            if (p.getId().equals(player1.getId())) {
                foundPlayer = true;
                break;
            }
        }
        Assertions.assertTrue(foundPlayer, "Player should be associated with team");
        
        // Verify match association
        Assertions.assertNotNull(retrievedTeam.getMatches());
        boolean foundMatch = false;
        for (MatchDTO m : retrievedTeam.getMatches()) {
            if (m.getId().equals(match.getId())) {
                foundMatch = true;
                break;
            }
        }
        Assertions.assertTrue(foundMatch, "Match should be associated with team");
        
        // Clean up created entities
        restTemplate.delete("/api/matches/{id}", match.getId());
        restTemplate.delete("/api/players/{id}", player1.getId());
        restTemplate.delete("/api/players/{id}", player2.getId());
        restTemplate.delete("/api/teams/{id}", team1.getId());
        restTemplate.delete("/api/teams/{id}", team2.getId());
        restTemplate.delete("/api/coaches/{id}", coach.getId());
        restTemplate.delete("/api/leagues/{id}", league.getId());
    }
    
    // === Helper Methods ===
    /**
     * Helper method to create a League, Coach, and Team for use in other tests.
     * This simplifies tests that require an existing team.
     *
     * @return The ID of the newly created Team.
     */
    private Long createTeamForTesting() {
        // Create a league
        LeagueCreateUpdateDTO leagueDTO = new LeagueCreateUpdateDTO();
        leagueDTO.setName("Test League");
        ResponseEntity<LeagueDTO> leagueResponse = restTemplate.postForEntity("/api/leagues", leagueDTO, LeagueDTO.class);
        LeagueDTO league = leagueResponse.getBody();
        Assertions.assertNotNull(league);
        
        // Create a coach
        CoachCreateUpdateDTO coachDTO = new CoachCreateUpdateDTO();
        coachDTO.setName("Test Coach");
        ResponseEntity<CoachDTO> coachResponse = restTemplate.postForEntity("/api/coaches", coachDTO, CoachDTO.class);
        CoachDTO coach = coachResponse.getBody();
        Assertions.assertNotNull(coach);
        
        // Create a team
        TeamCreateUpdateDTO teamDTO = new TeamCreateUpdateDTO();
        teamDTO.setName("Test Team");
        teamDTO.setCoachId(coach.getId());
        teamDTO.setLeagueId(league.getId());
        teamDTO.setPlayerIds(Collections.emptyList());
        
        ResponseEntity<TeamDTO> teamResponse = restTemplate.postForEntity("/api/teams", teamDTO, TeamDTO.class);
        TeamDTO team = teamResponse.getBody();
        Assertions.assertNotNull(team);
        
        return team.getId();
    }
}
