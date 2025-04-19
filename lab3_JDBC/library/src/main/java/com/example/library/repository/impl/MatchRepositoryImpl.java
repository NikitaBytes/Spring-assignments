package com.example.library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.library.model.Match;
import com.example.library.repository.MatchRepository;
import com.example.library.repository.rowmapper.MatchRowMapper;

/**
 * JDBC implementation of the MatchRepository interface.
 * Handles database operations for Match entities using JdbcTemplate.
 */
@Repository
public class MatchRepositoryImpl implements MatchRepository {

    private final JdbcTemplate jdbc;

    /**
     * Constructs a MatchRepositoryImpl with the given JdbcTemplate.
     *
     * @param jdbc The JdbcTemplate to use for database operations.
     */
    public MatchRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Match> findAll() {
        String sql = "SELECT id, home_team_id, away_team_id, match_date, home_score, away_score FROM matches";
        return jdbc.query(sql, new MatchRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Match findById(Long id) {
        String sql = "SELECT id, home_team_id, away_team_id, match_date, home_score, away_score FROM matches WHERE id = ?";
        return jdbc.queryForObject(sql, new MatchRowMapper(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Match match) {
        String sql = "INSERT INTO matches (home_team_id, away_team_id, match_date, home_score, away_score) VALUES (?, ?, ?, ?, ?)";
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, match.getHomeTeamId());
            ps.setLong(2, match.getAwayTeamId());
            ps.setObject(3, match.getMatchDate());
            ps.setInt(4, match.getHomeScore());
            ps.setInt(5, match.getAwayScore());
            return ps;
        }, kh);
        Number key = Objects.requireNonNull(
            kh.getKey(),
            "Failed to retrieve generated key for Match"
        );
        return key.longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Match match) {
        String sql = "UPDATE matches SET home_team_id = ?, away_team_id = ?, match_date = ?, home_score = ?, away_score = ? WHERE id = ?";
        return jdbc.update(sql,
                match.getHomeTeamId(),
                match.getAwayTeamId(),
                match.getMatchDate(),
                match.getHomeScore(),
                match.getAwayScore(),
                match.getId()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM matches WHERE id = ?";
        return jdbc.update(sql, id);
    }
}