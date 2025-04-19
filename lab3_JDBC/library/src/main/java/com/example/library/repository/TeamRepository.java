package com.example.library.repository;

import java.util.List;

import com.example.library.model.Team;

/**
 * Interface defining the data access operations for the Team entity.
 */
public interface TeamRepository {
    /**
     * Retrieves all teams from the database.
     *
     * @return A list of all teams.
     */
    List<Team> findAll();

    /**
     * Finds a team by its unique identifier.
     *
     * @param id The ID of the team to find.
     * @return The found Team object, or null if not found.
     */
    Team findById(Long id);

    /**
     * Creates a new team in the database.
     *
     * @param team The Team object to create.
     * @return The generated ID of the newly created team.
     */
    Long create(Team team);

    /**
     * Updates an existing team in the database.
     *
     * @param team The Team object with updated information.
     * @return The number of rows affected (should be 1 if successful).
     */
    int update(Team team);

    /**
     * Deletes a team from the database by its ID.
     *
     * @param id The ID of the team to delete.
     * @return The number of rows affected (should be 1 if successful).
     */
    int delete(Long id);
}