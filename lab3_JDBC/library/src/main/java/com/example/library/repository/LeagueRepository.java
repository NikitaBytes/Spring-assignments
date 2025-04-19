package com.example.library.repository;

import java.util.List;

import com.example.library.model.League;

/**
 * Interface defining the data access operations for the League entity.
 */
public interface LeagueRepository {
    /**
     * Retrieves all leagues from the database.
     *
     * @return A list of all leagues.
     */
    List<League> findAll();

    /**
     * Finds a league by its unique identifier.
     *
     * @param id The ID of the league to find.
     * @return The found League object, or null if not found.
     */
    League findById(Long id);

    /**
     * Creates a new league in the database.
     *
     * @param league The League object to create.
     * @return The generated ID of the newly created league.
     */
    Long create(League league);

    /**
     * Updates an existing league in the database.
     *
     * @param league The League object with updated information.
     * @return The number of rows affected (should be 1 if successful).
     */
    int update(League league);

    /**
     * Deletes a league from the database by its ID.
     *
     * @param id The ID of the league to delete.
     * @return The number of rows affected (should be 1 if successful).
     */
    int delete(Long id);
}