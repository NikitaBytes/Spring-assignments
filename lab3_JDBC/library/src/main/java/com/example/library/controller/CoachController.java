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

import com.example.library.dto.CoachCreateUpdateDTO;
import com.example.library.dto.CoachDTO;
import com.example.library.service.CoachService;

import jakarta.validation.Valid;

/**
 * REST controller for managing coaches.
 * Provides endpoints for CRUD operations on coaches.
 */
@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    private final CoachService coachService;

    /**
     * Constructor for CoachController.
     *
     * @param coachService The service layer for coach operations.
     */
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    /**
     * Retrieves all coaches.
     *
     * @return ResponseEntity containing a list of CoachDTOs.
     */
    @GetMapping
    public ResponseEntity<List<CoachDTO>> getAll() {
        return ResponseEntity.ok(coachService.findAll());
    }

    /**
     * Retrieves a coach by its ID.
     *
     * @param id The ID of the coach to retrieve.
     * @return ResponseEntity containing the CoachDTO if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CoachDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(coachService.findById(id));
    }

    /**
     * Creates a new coach.
     *
     * @param dto The CoachCreateUpdateDTO containing the data for the new coach.
     * @return ResponseEntity containing the created CoachDTO.
     */
    @PostMapping
    public ResponseEntity<CoachDTO> create(@Valid @RequestBody CoachCreateUpdateDTO dto) {
        CoachDTO created = coachService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing coach.
     *
     * @param id  The ID of the coach to update.
     * @param dto The CoachCreateUpdateDTO containing the updated data.
     * @return ResponseEntity containing the updated CoachDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CoachDTO> update(@PathVariable("id") Long id,
                                           @Valid @RequestBody CoachCreateUpdateDTO dto) {
        return ResponseEntity.ok(coachService.update(id, dto));
    }

    /**
     * Deletes a coach.
     *
     * @param id The ID of the coach to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        coachService.delete(id);
        return ResponseEntity.noContent().build();
    }
}