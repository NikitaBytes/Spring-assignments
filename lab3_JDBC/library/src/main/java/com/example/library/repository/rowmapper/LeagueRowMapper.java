package com.example.library.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.example.library.model.League;

/**
 * RowMapper implementation for mapping database rows to League objects.
 */
public class LeagueRowMapper implements RowMapper<League> {
    /**
     * Maps a single row of the ResultSet to a League object.
     *
     * @param rs     The ResultSet to map from.
     * @param rowNum The number of the current row.
     * @return The mapped League object.
     * @throws SQLException If a SQLException is encountered getting column values.
     */
    @Override
    public League mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        League l = new League();
        l.setId(rs.getLong("id"));
        l.setName(rs.getString("name"));
        return l;
    }
}