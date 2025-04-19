package com.example.library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.library.model.Coach;
import com.example.library.repository.CoachRepository;
import com.example.library.repository.rowmapper.CoachRowMapper;

/**
 * JDBC implementation of the CoachRepository interface.
 * Handles database operations for Coach entities using JdbcTemplate.
 */
@Repository
public class CoachRepositoryImpl implements CoachRepository {

    private final JdbcTemplate jdbc;

    /**
     * Constructs a CoachRepositoryImpl with the given JdbcTemplate.
     *
     * @param jdbc The JdbcTemplate to use for database operations.
     */
    public CoachRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Coach> findAll() {
        String sql = "SELECT id, name FROM coaches";
        return jdbc.query(sql, new CoachRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coach findById(Long id) {
        String sql = "SELECT id, name FROM coaches WHERE id = ?";
        return jdbc.queryForObject(sql, new CoachRowMapper(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Coach coach) {
        String sql = "INSERT INTO coaches (name) VALUES (?)";
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, coach.getName());
            return ps;
        }, kh);
        Number key = Objects.requireNonNull(
            kh.getKey(),
            "Failed to retrieve generated key for Coach"
        );
        return key.longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Coach coach) {
        String sql = "UPDATE coaches SET name = ? WHERE id = ?";
        return jdbc.update(sql,
                coach.getName(),
                coach.getId()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM coaches WHERE id = ?";
        return jdbc.update(sql, id);
    }
}