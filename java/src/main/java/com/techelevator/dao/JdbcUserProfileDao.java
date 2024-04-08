package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;

import org.springframework.dao.DataIntegrityViolationException;
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
  /*
   user_profile_id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users (user_id),
  first_name varchar(50),
  last_name varchar(50),
  email varchar(200),
  profile_picture varchar(500) NOT NULL,
  goal varchar(50) not null
   */

  @Override
  public UserProfile createProfile(UserProfile newProfile) {
    UserProfile profileToCreate = null;
    String sql = "insert into user_profiles (first_name, last_name, email, " +
            " goal) values(?,?,?,?) returning user_profile_id; ";
    try{
      int newId = jdbcTemplate.queryForObject(sql,int.class,newProfile.getFirstName(),newProfile.getLastName(),newProfile.getEmail()
              ,newProfile.getGoal());
      profileToCreate = getProfile(newId);
    }catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }

    return profileToCreate;
  }

  @Override
  public List<Workout> getWorkouts(int userId) {
    throw new UnsupportedOperationException("Unimplemented method 'getWorkouts'");
  }

  private UserProfile mapRowToProfile(SqlRowSet results) {
    UserProfile profile = new UserProfile();

    return profile;
  }
}
