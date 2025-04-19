package com.example.library.repository;

import java.util.List;

import com.example.library.model.Match;

/**
 * Interface defining the data access operations for the Match entity.
 */
public interface MatchRepository {
    /**
     * Retrieves all matches from the database.
     *
     * @return A list of all matches.
     */
    List<Match> findAll();

    /**
     * Finds a match by its unique identifier.
     *
     * @param id The ID of the match to find.
     * @return The found Match object, or null if not found.
     */
    Match findById(Long id);

    /**
     * Creates a new match in the database.
     *
     * @param match The Match object to create.
     * @return The generated ID of the newly created match.
     */
    Long create(Match match);

    /**
     * Updates an existing match in the database.
     *
     * @param match The Match object with updated information.
     * @return The number of rows affected (should be 1 if successful).
     */
    int update(Match match);

    /**
     * Deletes a match from the database by its ID.
     *
     * @param id The ID of the match to delete.
     * @return The number of rows affected (should be 1 if successful).
     */
    int delete(Long id);
}