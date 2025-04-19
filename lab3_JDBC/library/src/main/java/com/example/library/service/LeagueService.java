package com.example.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.library.dto.LeagueCreateUpdateDTO;
import com.example.library.dto.LeagueDTO;
import com.example.library.model.League;
import com.example.library.repository.LeagueRepository;

/**
 * Service layer for managing League entities.
 * Handles business logic related to leagues, including CRUD operations and data transformation.
 */
@Service
public class LeagueService {
    private final LeagueRepository repo;

    /**
     * Constructs a LeagueService with the LeagueRepository.
     *
     * @param repo Repository for league data access.
     */
    public LeagueService(LeagueRepository repo) {
        this.repo = repo;
    }

    /**
     * Retrieves all leagues and converts them to DTOs.
     *
     * @return A list of LeagueDTOs.
     */
    public List<LeagueDTO> findAll() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds a league by its ID and converts it to a DTO.
     *
     * @param id The ID of the league to find.
     * @return The LeagueDTO corresponding to the found league.
     * @throws NotFoundException if the league with the given ID is not found.
     */
    public LeagueDTO findById(Long id) {
        try {
            return toDTO(repo.findById(id));
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("League not found with id " + id);
        }
    }

    /**
     * Creates a new league based on the provided DTO.
     *
     * @param dto The DTO containing data for the new league.
     * @return The DTO of the newly created league.
     */
    public LeagueDTO create(LeagueCreateUpdateDTO dto) {
        League l = new League();
        l.setName(dto.getName());
        long id = repo.create(l);
        return toDTO(repo.findById(id));
    }

    /**
     * Updates an existing league with the data from the provided DTO.
     * Validates the existence of the league.
     *
     * @param id  The ID of the league to update.
     * @param dto The DTO containing the updated data.
     * @return The DTO of the updated league.
     * @throws NotFoundException if the league with the given ID is not found.
     */
    public LeagueDTO update(Long id, LeagueCreateUpdateDTO dto) {
        // проверяем, что лига есть
        findById(id);
        League l = new League();
        l.setId(id);
        l.setName(dto.getName());
        repo.update(l);
        return toDTO(repo.findById(id));
    }

    /**
     * Deletes a league by its ID.
     * Validates the existence of the league before deletion.
     *
     * @param id The ID of the league to delete.
     * @throws NotFoundException if the league with the given ID is not found.
     */
    public void delete(Long id) {
        // проверяем, что лига есть
        findById(id);
        repo.delete(id);
    }

    /**
     * Converts a League entity to a LeagueDTO.
     *
     * @param l The League entity to convert.
     * @return The corresponding LeagueDTO.
     */
    private LeagueDTO toDTO(League l) {
        LeagueDTO dto = new LeagueDTO();
        dto.setId(l.getId());
        dto.setName(l.getName());
        return dto;
    }
}