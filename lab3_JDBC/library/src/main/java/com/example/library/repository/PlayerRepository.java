package com.example.library.repository;

import java.util.List;

import com.example.library.model.Player;

/**
 * Interface defining the data access operations for the Player entity.
 */
public interface PlayerRepository {
    /**
     * Retrieves all players from the database.
     *
     * @return A list of all players.
     */
    List<Player> findAll();

    /**
     * Finds a player by its unique identifier.
     *
     * @param id The ID of the player to find.
     * @return The found Player object, or null if not found.
     */
    Player findById(Long id);

    /**
     * Creates a new player in the database.
     *
     * @param player The Player object to create.
     * @return The generated ID of the newly created player.
     */
    Long create(Player player);

    /**
     * Updates an existing player in the database.
     *
     * @param player The Player object with updated information.
     * @return The number of rows affected (should be 1 if successful).
     */
    int update(Player player);

    /**
     * Deletes a player from the database by its ID.
     *
     * @param id The ID of the player to delete.
     * @return The number of rows affected (should be 1 if successful).
     */
    int delete(Long id);
}