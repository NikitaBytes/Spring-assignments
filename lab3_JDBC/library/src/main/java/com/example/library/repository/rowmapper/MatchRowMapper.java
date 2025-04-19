package com.example.library.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.example.library.model.Match;

/**
 * RowMapper implementation for mapping database rows to Match objects.
 */
public class MatchRowMapper implements RowMapper<Match> {
    /**
     * Maps a single row of the ResultSet to a Match object.
     *
     * @param rs     The ResultSet to map from.
     * @param rowNum The number of the current row.
     * @return The mapped Match object.
     * @throws SQLException If a SQLException is encountered getting column values.
     */
    @Override
    public Match mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Match m = new Match();
        m.setId(rs.getLong("id"));
        m.setHomeTeamId(rs.getLong("home_team_id"));
        m.setAwayTeamId(rs.getLong("away_team_id"));
        m.setMatchDate(rs.getTimestamp("match_date").toLocalDateTime());
        m.setHomeScore(rs.getInt("home_score"));
        m.setAwayScore(rs.getInt("away_score"));
        return m;
    }
}