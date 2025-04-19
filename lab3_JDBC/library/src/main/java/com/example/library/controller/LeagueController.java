package com.example.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.LeagueCreateUpdateDTO;
import com.example.library.dto.LeagueDTO;
import com.example.library.service.LeagueService;

import jakarta.validation.Valid;

/**
 * REST controller for managing leagues.
 * Provides endpoints for CRUD operations on leagues.
 */
@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    private final LeagueService leagueService;

    /**
     * Constructor for LeagueController.
     *
     * @param leagueService The service layer for league operations.
     */
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    /**
     * Retrieves all leagues.
     *
     * @return A list of LeagueDTOs.
     */
    @GetMapping
    public List<LeagueDTO> getAll() {
        return leagueService.findAll();
    }

    /**
     * Retrieves a league by its ID.
     *
     * @param id The ID of the league to retrieve.
     * @return The LeagueDTO if found.
     */
    @GetMapping("/{id}")
    public LeagueDTO getById(@PathVariable("id") Long id) {
        return leagueService.findById(id);
    }

    /**
     * Creates a new league.
     *
     * @param dto The LeagueCreateUpdateDTO containing the data for the new league.
     * @return The created LeagueDTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeagueDTO create(@Valid @RequestBody LeagueCreateUpdateDTO dto) {
        return leagueService.create(dto);
    }

    /**
     * Updates an existing league.
     *
     * @param id  The ID of the league to update.
     * @param dto The LeagueCreateUpdateDTO containing the updated data.
     * @return The updated LeagueDTO.
     */
    @PutMapping("/{id}")
    public LeagueDTO update(@PathVariable("id") Long id,
                            @Valid @RequestBody LeagueCreateUpdateDTO dto) {
        return leagueService.update(id, dto);
    }

    /**
     * Deletes a league.
     *
     * @param id The ID of the league to delete.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        leagueService.delete(id);
    }
}