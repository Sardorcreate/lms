package com.company.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JDBCRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String select(Integer id) {

        String query = "DELETE FROM mark WHERE id = " + id;

        jdbcTemplate.update(query);

        return "updated successfully!!!";
    }

    public Integer update(Integer id, Integer mark) {

        String query = "UPDATE mark SET score = ? WHERE id = ?";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, mark);
                ps.setInt(2, id);
            }
        };

        int update = jdbcTemplate.update(query, preparedStatementSetter);

        return update;
    }
}
