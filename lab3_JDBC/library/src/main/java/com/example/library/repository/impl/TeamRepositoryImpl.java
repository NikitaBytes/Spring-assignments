package com.example.library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.library.model.Team;
import com.example.library.repository.TeamRepository;
import com.example.library.repository.rowmapper.TeamRowMapper;

/**
 * JDBC implementation of the TeamRepository interface.
 * Handles database operations for Team entities using JdbcTemplate.
 */
@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private final JdbcTemplate jdbc;

    /**
     * Constructs a TeamRepositoryImpl with the given JdbcTemplate.
     *
     * @param jdbc The JdbcTemplate to use for database operations.
     */
    public TeamRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Team> findAll() {
        String sql = "SELECT id, name, coach_id, league_id FROM teams";
        return jdbc.query(sql, new TeamRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Team findById(Long id) {
        String sql = "SELECT id, name, coach_id, league_id FROM teams WHERE id = ?";
        return jdbc.queryForObject(sql, new TeamRowMapper(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Team team) {
        String sql = "INSERT INTO teams (name, coach_id, league_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, team.getName());
            ps.setLong(2, team.getCoachId());
            ps.setLong(3, team.getLeagueId());
            return ps;
        }, keyHolder);
        Number key = Objects.requireNonNull(
            keyHolder.getKey(),
            "Failed to retrieve generated key for Team"
        );
        return key.longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Team team) {
        String sql = "UPDATE teams SET name = ?, coach_id = ?, league_id = ? WHERE id = ?";
        return jdbc.update(sql,
                team.getName(),
                team.getCoachId(),
                team.getLeagueId(),
                team.getId()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM teams WHERE id = ?";
        return jdbc.update(sql, id);
    }
}