package com.example.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.dto.PlayerCreateUpdateDTO;
import com.example.library.dto.PlayerDTO;
import com.example.library.model.Player;
import com.example.library.repository.PlayerRepository;
import com.example.library.repository.TeamRepository;

/**
 * Service layer for managing Player entities.
 * Handles business logic related to players, including CRUD operations and data transformation.
 */
@Service
@Transactional
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    /**
     * Constructs a PlayerService with necessary repositories.
     *
     * @param playerRepository Repository for player data access.
     * @param teamRepository   Repository for team data access (to validate team existence).
     */
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    /**
     * Retrieves all players and converts them to DTOs.
     *
     * @return A list of PlayerDTOs.
     */
    public List<PlayerDTO> findAll() {
        return playerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds a player by its ID and converts it to a DTO.
     *
     * @param id The ID of the player to find.
     * @return The PlayerDTO corresponding to the found player.
     * @throws NotFoundException if the player with the given ID is not found.
     */
    public PlayerDTO findById(Long id) {
        return toDTO(findPlayerOrThrow(id));
    }

    /**
     * Creates a new player based on the provided DTO.
     * Validates the existence of the associated team.
     *
     * @param dto The DTO containing data for the new player.
     * @return The DTO of the newly created player.
     * @throws NotFoundException if the associated team is not found.
     */
    public PlayerDTO create(PlayerCreateUpdateDTO dto) {
        teamRepository.findById(dto.getTeamId());
        Player player = new Player();
        player.setName(dto.getName());
        player.setTeamId(dto.getTeamId());
        Long id = playerRepository.create(player);
        return findById(id);
    }

    /**
     * Updates an existing player with the data from the provided DTO.
     * Validates the existence of the player and the associated team.
     *
     * @param id  The ID of the player to update.
     * @param dto The DTO containing the updated data.
     * @return The DTO of the updated player.
     * @throws NotFoundException if the player or the associated team is not found.
     */
    public PlayerDTO update(Long id, PlayerCreateUpdateDTO dto) {
        Player existing = findPlayerOrThrow(id);
        teamRepository.findById(dto.getTeamId());
        existing.setName(dto.getName());
        existing.setTeamId(dto.getTeamId());
        playerRepository.update(existing);
        return findById(id);
    }

    /**
     * Deletes a player by its ID.
     *
     * @param id The ID of the player to delete.
     * @throws NotFoundException if the player with the given ID is not found.
     */
    public void delete(Long id) {
        int deleted = playerRepository.delete(id);
        if (deleted == 0) {
            throw new NotFoundException("Player not found with id " + id);
        }
    }

    /**
     * Finds a player by ID or throws a NotFoundException if not found.
     *
     * @param id The ID of the player to find.
     * @return The found Player entity.
     * @throws NotFoundException if the player is not found.
     */
    private Player findPlayerOrThrow(Long id) {
        try {
            return playerRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException("Player not found with id " + id);
        }
    }

    /**
     * Converts a Player entity to a PlayerDTO.
     *
     * @param player The Player entity to convert.
     * @return The corresponding PlayerDTO.
     */
    private PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        return dto;
    }
}