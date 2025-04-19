package com.example.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.TeamCreateUpdateDTO;
import com.example.library.dto.TeamDTO;
import com.example.library.service.TeamService;

import jakarta.validation.Valid;

/**
 * REST controller for managing teams.
 * Provides endpoints for CRUD operations on teams.
 */
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    /**
     * Constructor for TeamController.
     *
     * @param teamService The service layer for team operations.
     */
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Retrieves all teams.
     *
     * @return ResponseEntity containing a list of TeamDTOs.
     */
    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAll() {
        return ResponseEntity.ok(teamService.findAll());
    }

    /**
     * Retrieves a team by its ID.
     *
     * @param id The ID of the team to retrieve.
     * @return ResponseEntity containing the TeamDTO if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teamService.findById(id));
    }

    /**
     * Creates a new team.
     *
     * @param dto The TeamCreateUpdateDTO containing the data for the new team.
     * @return ResponseEntity containing the created TeamDTO.
     */
    @PostMapping
    public ResponseEntity<TeamDTO> create(@Valid @RequestBody TeamCreateUpdateDTO dto) {
        TeamDTO created = teamService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing team.
     *
     * @param id  The ID of the team to update.
     * @param dto The TeamCreateUpdateDTO containing the updated data.
     * @return ResponseEntity containing the updated TeamDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable("id") Long id,
                                          @Valid @RequestBody TeamCreateUpdateDTO dto) {
        return ResponseEntity.ok(teamService.update(id, dto));
    }

    /**
     * Deletes a team.
     *
     * @param id The ID of the team to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}