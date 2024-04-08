package com.techelevator.dao;

import com.techelevator.model.UserProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcProfileDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    UserProfile getProfile(int id) {
        String sql = "select * from user_profile " +
                "join user_goals using(user_id) " +
                "where user_id = ?;";
        try { // catch the joker
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

            if(results.next()) {
                return mapRowToProfile(results)
            }
        }
    }

    UserProfile updateProfile(int id, UserProfile profileToUpdate) {
        return null;
    }

    void deleteProfile(int id) {

    }

    UserProfile createProfile(UserProfile newProfile) {
        return null;
    }

    private UserProfile mapRowToProfile(SqlRowSet results) {

    }

    public static class JdbcExerciseDao {
    }
}
