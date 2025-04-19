package com.example.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.library.dto.CoachCreateUpdateDTO;
import com.example.library.dto.CoachDTO;
import com.example.library.model.Coach;
import com.example.library.repository.CoachRepository;

/**
 * Service layer for managing Coach entities.
 * Handles business logic related to coaches, including CRUD operations and data transformation.
 */
@Service
public class CoachService {
    private final CoachRepository repo;

    /**
     * Constructs a CoachService with the CoachRepository.
     *
     * @param repo Repository for coach data access.
     */
    public CoachService(CoachRepository repo) {
        this.repo = repo;
    }

    /**
     * Retrieves all coaches and converts them to DTOs.
     *
     * @return A list of CoachDTOs.
     */
    public List<CoachDTO> findAll() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds a coach by its ID and converts it to a DTO.
     *
     * @param id The ID of the coach to find.
     * @return The CoachDTO corresponding to the found coach.
     * @throws NotFoundException if the coach with the given ID is not found.
     */
    public CoachDTO findById(Long id) {
        try {
            return toDTO(repo.findById(id));
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Coach not found with id " + id);
        }
    }

    /**
     * Creates a new coach based on the provided DTO.
     *
     * @param dto The DTO containing data for the new coach.
     * @return The DTO of the newly created coach.
     */
    public CoachDTO create(CoachCreateUpdateDTO dto) {
        Coach c = new Coach();
        c.setName(dto.getName());
        long id = repo.create(c);
        return toDTO(repo.findById(id));
    }

    /**
     * Updates an existing coach with the data from the provided DTO.
     * Validates the existence of the coach.
     *
     * @param id  The ID of the coach to update.
     * @param dto The DTO containing the updated data.
     * @return The DTO of the updated coach.
     * @throws NotFoundException if the coach with the given ID is not found.
     */
    public CoachDTO update(Long id, CoachCreateUpdateDTO dto) {
        // проверяем, что такой коуч существует
        findById(id);
        Coach c = new Coach();
        c.setId(id);
        c.setName(dto.getName());
        repo.update(c);
        return toDTO(repo.findById(id));
    }

    /**
     * Deletes a coach by its ID.
     * Validates the existence of the coach before deletion.
     *
     * @param id The ID of the coach to delete.
     * @throws NotFoundException if the coach with the given ID is not found.
     */
    public void delete(Long id) {
        // если нет такой записи — бросим 404
        findById(id);
        repo.delete(id);
    }

    /**
     * Converts a Coach entity to a CoachDTO.
     *
     * @param c The Coach entity to convert.
     * @return The corresponding CoachDTO.
     */
    private CoachDTO toDTO(Coach c) {
        CoachDTO dto = new CoachDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        return dto;
    }
}