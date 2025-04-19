package com.example.library.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.example.library.model.Coach;

/**
 * RowMapper implementation for mapping database rows to Coach objects.
 */
public class CoachRowMapper implements RowMapper<Coach> {
    /**
     * Maps a single row of the ResultSet to a Coach object.
     *
     * @param rs     The ResultSet to map from.
     * @param rowNum The number of the current row.
     * @return The mapped Coach object.
     * @throws SQLException If a SQLException is encountered getting column values.
     */
    @Override
    public Coach mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Coach c = new Coach();
        c.setId(rs.getLong("id"));
        c.setName(rs.getString("name"));
        return c;
    }
}