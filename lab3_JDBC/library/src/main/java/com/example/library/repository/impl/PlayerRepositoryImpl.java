package com.example.library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.library.model.Player;
import com.example.library.repository.PlayerRepository;
import com.example.library.repository.rowmapper.PlayerRowMapper;

/**
 * JDBC implementation of the PlayerRepository interface.
 * Handles database operations for Player entities using JdbcTemplate.
 */
@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    private final JdbcTemplate jdbc;

    /**
     * Constructs a PlayerRepositoryImpl with the given JdbcTemplate.
     *
     * @param jdbc The JdbcTemplate to use for database operations.
     */
    public PlayerRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> findAll() {
        String sql = "SELECT id, name, team_id FROM players";
        return jdbc.query(sql, new PlayerRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player findById(Long id) {
        String sql = "SELECT id, name, team_id FROM players WHERE id = ?";
        return jdbc.queryForObject(sql, new PlayerRowMapper(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Player player) {
        String sql = "INSERT INTO players (name, team_id) VALUES (?, ?)";
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, player.getName());
            ps.setLong(2, player.getTeamId());
            return ps;
        }, kh);
        Number key = Objects.requireNonNull(
            kh.getKey(),
            "Failed to retrieve generated key for Player"
        );
        return key.longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Player player) {
        String sql = "UPDATE players SET name = ?, team_id = ? WHERE id = ?";
        return jdbc.update(sql,
                player.getName(),
                player.getTeamId(),
                player.getId()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM players WHERE id = ?";
        return jdbc.update(sql, id);
    }
}