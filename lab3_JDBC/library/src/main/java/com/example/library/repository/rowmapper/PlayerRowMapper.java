package com.example.library.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.example.library.model.Player;

/**
 * RowMapper implementation for mapping database rows to Player objects.
 */
public class PlayerRowMapper implements RowMapper<Player> {
    /**
     * Maps a single row of the ResultSet to a Player object.
     *
     * @param rs     The ResultSet to map from.
     * @param rowNum The number of the current row.
     * @return The mapped Player object.
     * @throws SQLException If a SQLException is encountered getting column values.
     */
    @Override
    public Player mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Player p = new Player();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setTeamId(rs.getLong("team_id"));
        return p;
    }
}