package com.example.library.repository;

import java.util.List;

import com.example.library.model.Coach;

/**
 * Interface defining the data access operations for the Coach entity.
 */
public interface CoachRepository {
    /**
     * Retrieves all coaches from the database.
     *
     * @return A list of all coaches.
     */
    List<Coach> findAll();

    /**
     * Finds a coach by its unique identifier.
     *
     * @param id The ID of the coach to find.
     * @return The found Coach object, or null if not found.
     */
    Coach findById(Long id);

    /**
     * Creates a new coach in the database.
     *
     * @param coach The Coach object to create.
     * @return The generated ID of the newly created coach.
     */
    Long create(Coach coach);

    /**
     * Updates an existing coach in the database.
     *
     * @param coach The Coach object with updated information.
     * @return The number of rows affected (should be 1 if successful).
     */
    int update(Coach coach);

    /**
     * Deletes a coach from the database by its ID.
     *
     * @param id The ID of the coach to delete.
     * @return The number of rows affected (should be 1 if successful).
     */
    int delete(Long id);
}