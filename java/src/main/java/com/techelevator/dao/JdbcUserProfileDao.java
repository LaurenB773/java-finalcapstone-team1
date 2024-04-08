package com.techelevator.dao;

import com.techelevator.model.UserProfile;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.sql.DataSource;

@Component
public class JdbcUserProfileDao implements UserProfileDao {

  private final JdbcTemplate jdbcTemplate;

  public JdbcUserProfileDao(DataSource datasource) {
    jdbcTemplate = new JdbcTemplate(datasource);
  }

  @Override
  public UserProfile getProfile(int userId) {
    String sql = "select * from user_profile " +
        "join user_goals using(user_id) " +
        "where user_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

      if (results.next()) {
        return mapRowToProfile(results);
      }
    } catch (CannotGetJdbcConnectionException e) {

    }

    return null;
  }

  @Override
  public List<UserProfile> getMembers() {
    throw new UnsupportedOperationException("Unimplemented method 'getMembers'");
  }

  @Override
  public UserProfile updateProfile(int userId, UserProfile profileToUpdate) {
    throw new UnsupportedOperationException("Unimplemented method 'updateProfile'");
  }

  @Override
  public void deleteProfile(int userId) {
    throw new UnsupportedOperationException("Unimplemented method 'deleteProfile'");
  }

  @Override
  public UserProfile createProfile(UserProfile newProfile) {
    throw new UnsupportedOperationException("Unimplemented method 'createProfile'");
  }

  private UserProfile mapRowToProfile(SqlRowSet results) {
    UserProfile profile = new UserProfile();
    return profile;
  }
}
