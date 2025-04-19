package com.example.library.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.example.library.model.Team;

/**
 * RowMapper implementation for mapping database rows to Team objects.
 */
public class TeamRowMapper implements RowMapper<Team> {
    /**
     * Maps a single row of the ResultSet to a Team object.
     *
     * @param rs     The ResultSet to map from.
     * @param rowNum The number of the current row.
     * @return The mapped Team object.
     * @throws SQLException If a SQLException is encountered getting column values.
     */
    @Override
    public Team mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Team t = new Team();
        t.setId(rs.getLong("id"));
        t.setName(rs.getString("name"));
        t.setCoachId(rs.getLong("coach_id"));
        t.setLeagueId(rs.getLong("league_id"));
        return t;
    }
}