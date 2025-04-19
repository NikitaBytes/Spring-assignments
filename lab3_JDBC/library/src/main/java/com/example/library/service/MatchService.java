package com.example.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.dto.MatchCreateUpdateDTO;
import com.example.library.dto.MatchDTO;
import com.example.library.model.Match;
import com.example.library.repository.MatchRepository;
import com.example.library.repository.TeamRepository;

/**
 * Service layer for managing Match entities.
 * Handles business logic related to matches, including CRUD operations and data transformation.
 */
@Service
@Transactional
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    /**
     * Constructs a MatchService with necessary repositories.
     *
     * @param matchRepository Repository for match data access.
     * @param teamRepository  Repository for team data access (to validate team existence).
     */
    public MatchService(MatchRepository matchRepository,
                        TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    /**
     * Retrieves all matches and converts them to DTOs.
     *
     * @return A list of MatchDTOs.
     */
    public List<MatchDTO> findAll() {
        return matchRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds a match by its ID and converts it to a DTO.
     *
     * @param id The ID of the match to find.
     * @return The MatchDTO corresponding to the found match.
     * @throws NotFoundException if the match with the given ID is not found.
     */
    public MatchDTO findById(Long id) {
        return toDTO(findMatchOrThrow(id));
    }

    /**
     * Creates a new match based on the provided DTO.
     * Validates the existence of the home and away teams.
     *
     * @param dto The DTO containing data for the new match.
     * @return The DTO of the newly created match.
     * @throws NotFoundException if the home or away team is not found.
     */
    public MatchDTO create(MatchCreateUpdateDTO dto) {
        teamRepository.findById(dto.getHomeTeamId());
        teamRepository.findById(dto.getAwayTeamId());
        Match match = new Match();
        match.setHomeTeamId(dto.getHomeTeamId());
        match.setAwayTeamId(dto.getAwayTeamId());
        match.setMatchDate(dto.getMatchDate());
        match.setHomeScore(dto.getHomeScore());
        match.setAwayScore(dto.getAwayScore());
        Long id = matchRepository.create(match);
        return findById(id);
    }

    /**
     * Updates an existing match with the data from the provided DTO.
     * Validates the existence of the match, home team, and away team.
     *
     * @param id  The ID of the match to update.
     * @param dto The DTO containing the updated data.
     * @return The DTO of the updated match.
     * @throws NotFoundException if the match, home team, or away team is not found.
     */
    public MatchDTO update(Long id, MatchCreateUpdateDTO dto) {
        Match existing = findMatchOrThrow(id);
        teamRepository.findById(dto.getHomeTeamId());
        teamRepository.findById(dto.getAwayTeamId());
        existing.setHomeTeamId(dto.getHomeTeamId());
        existing.setAwayTeamId(dto.getAwayTeamId());
        existing.setMatchDate(dto.getMatchDate());
        existing.setHomeScore(dto.getHomeScore());
        existing.setAwayScore(dto.getAwayScore());
        matchRepository.update(existing);
        return findById(id);
    }

    /**
     * Deletes a match by its ID.
     *
     * @param id The ID of the match to delete.
     * @throws NotFoundException if the match with the given ID is not found.
     */
    public void delete(Long id) {
        int deleted = matchRepository.delete(id);
        if (deleted == 0) {
            throw new NotFoundException("Match not found with id " + id);
        }
    }

    /**
     * Finds a match by ID or throws a NotFoundException if not found.
     *
     * @param id The ID of the match to find.
     * @return The found Match entity.
     * @throws NotFoundException if the match is not found.
     */
    private Match findMatchOrThrow(Long id) {
        try {
            return matchRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException("Match not found with id " + id);
        }
    }

    /**
     * Converts a Match entity to a MatchDTO.
     *
     * @param match The Match entity to convert.
     * @return The corresponding MatchDTO.
     */
    private MatchDTO toDTO(Match match) {
        MatchDTO dto = new MatchDTO();
        dto.setId(match.getId());
        dto.setHomeTeamId(match.getHomeTeamId());
        dto.setAwayTeamId(match.getAwayTeamId());
        dto.setMatchDate(match.getMatchDate());
        dto.setHomeScore(match.getHomeScore());
        dto.setAwayScore(match.getAwayScore());
        return dto;
    }
}