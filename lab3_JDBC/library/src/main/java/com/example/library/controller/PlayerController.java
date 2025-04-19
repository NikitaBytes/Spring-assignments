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

import com.example.library.dto.PlayerCreateUpdateDTO;
import com.example.library.dto.PlayerDTO;
import com.example.library.service.PlayerService;

import jakarta.validation.Valid;

/**
 * REST controller for managing players.
 * Provides endpoints for CRUD operations on players.
 */
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    /**
     * Constructor for PlayerController.
     *
     * @param playerService The service layer for player operations.
     */
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Retrieves all players.
     *
     * @return ResponseEntity containing a list of PlayerDTOs.
     */
    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAll() {
        return ResponseEntity.ok(playerService.findAll());
    }

    /**
     * Retrieves a player by its ID.
     *
     * @param id The ID of the player to retrieve.
     * @return ResponseEntity containing the PlayerDTO if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(playerService.findById(id));
    }

    /**
     * Creates a new player.
     *
     * @param dto The PlayerCreateUpdateDTO containing the data for the new player.
     * @return ResponseEntity containing the created PlayerDTO.
     */
    @PostMapping
    public ResponseEntity<PlayerDTO> create(@Valid @RequestBody PlayerCreateUpdateDTO dto) {
        PlayerDTO created = playerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing player.
     *
     * @param id  The ID of the player to update.
     * @param dto The PlayerCreateUpdateDTO containing the updated data.
     * @return ResponseEntity containing the updated PlayerDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable("id") Long id,
                                            @Valid @RequestBody PlayerCreateUpdateDTO dto) {
        return ResponseEntity.ok(playerService.update(id, dto));
    }

    /**
     * Deletes a player.
     *
     * @param id The ID of the player to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}