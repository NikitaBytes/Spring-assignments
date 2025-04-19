package com.example.library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.library.model.League;
import com.example.library.repository.LeagueRepository;
import com.example.library.repository.rowmapper.LeagueRowMapper;

/**
 * JDBC implementation of the LeagueRepository interface.
 * Handles database operations for League entities using JdbcTemplate.
 */
@Repository
public class LeagueRepositoryImpl implements LeagueRepository {

    private final JdbcTemplate jdbc;

    /**
     * Constructs a LeagueRepositoryImpl with the given JdbcTemplate.
     *
     * @param jdbc The JdbcTemplate to use for database operations.
     */
    public LeagueRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<League> findAll() {
        String sql = "SELECT id, name FROM leagues";
        return jdbc.query(sql, new LeagueRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public League findById(Long id) {
        String sql = "SELECT id, name FROM leagues WHERE id = ?";
        return jdbc.queryForObject(sql, new LeagueRowMapper(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(League league) {
        String sql = "INSERT INTO leagues (name) VALUES (?)";
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, league.getName());
            return ps;
        }, kh);
        Number key = Objects.requireNonNull(
            kh.getKey(),
            "Failed to retrieve generated key for League"
        );
        return key.longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(League league) {
        String sql = "UPDATE leagues SET name = ? WHERE id = ?";
        return jdbc.update(sql,
                league.getName(),
                league.getId()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM leagues WHERE id = ?";
        return jdbc.update(sql, id);
    }
}