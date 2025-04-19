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

import com.example.library.dto.MatchCreateUpdateDTO;
import com.example.library.dto.MatchDTO;
import com.example.library.service.MatchService;

import jakarta.validation.Valid;

/**
 * REST controller for managing matches.
 * Provides endpoints for CRUD operations on matches.
 */
@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    /**
     * Constructor for MatchController.
     *
     * @param matchService The service layer for match operations.
     */
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Retrieves all matches.
     *
     * @return ResponseEntity containing a list of MatchDTOs.
     */
    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAll() {
        return ResponseEntity.ok(matchService.findAll());
    }

    /**
     * Retrieves a match by its ID.
     *
     * @param id The ID of the match to retrieve.
     * @return ResponseEntity containing the MatchDTO if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(matchService.findById(id));
    }

    /**
     * Creates a new match.
     *
     * @param dto The MatchCreateUpdateDTO containing the data for the new match.
     * @return ResponseEntity containing the created MatchDTO.
     */
    @PostMapping
    public ResponseEntity<MatchDTO> create(@Valid @RequestBody MatchCreateUpdateDTO dto) {
        MatchDTO created = matchService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing match.
     *
     * @param id  The ID of the match to update.
     * @param dto The MatchCreateUpdateDTO containing the updated data.
     * @return ResponseEntity containing the updated MatchDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> update(@PathVariable("id") Long id,
                                           @Valid @RequestBody MatchCreateUpdateDTO dto) {
        return ResponseEntity.ok(matchService.update(id, dto));
    }

    /**
     * Deletes a match.
     *
     * @param id The ID of the match to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        matchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}