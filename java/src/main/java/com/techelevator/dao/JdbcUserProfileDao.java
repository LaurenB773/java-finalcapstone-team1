package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/*
 * user_profile_id SERIAL PRIMARY KEY,
 * user_id INT REFERENCES users (user_id),
 * first_name varchar(50),
 * last_name varchar(50),
 * email varchar(200),
 * profile_picture varchar(500) NOT NULL,
 * goal varchar(50) not null
 */

@Component
public class JdbcUserProfileDao implements UserProfileDao {

  private final JdbcTemplate jdbcTemplate;

  public JdbcUserProfileDao(DataSource datasource) {
    jdbcTemplate = new JdbcTemplate(datasource);
  }

  @Override
  public UserProfile getProfile(String username) {
    String sql = "select * from user_profiles where user_id = (select user_id from users where username = ?);";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);

      if (results.next()) {
        return mapRowToProfile(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
    }

    return null;
  }


  public UserProfile getProfileById(int userId) {
    String sql = "select * from user_profiles where user_id = ?;";

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
    String sql = "select * from user_profiles;";
    List<UserProfile> profiles = new ArrayList<>();

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
        profiles.add(mapRowToProfile(results));
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    }

    return profiles;
  }

  @Override
  public List<Workout> getWorkouts(int userProfileId) {
    String sql = "select * from workouts where user_profile_id = ?;";
    List<Workout> workouts = new ArrayList<>();

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userProfileId);
      while (results.next()) {
        workouts.add(JdbcWorkoutDao.mapRowToWorkout(results));
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    }

    return workouts;
  }

  @Override
  public UserProfile createProfile(UserProfile newProfile, int id) {
    UserProfile profileToCreate = null;
    String sql = "insert into user_profiles (user_id,first_name, last_name, email, goal) values((select user_id from users where user_id = ?),?,?,?,?) returning user_profile_id; ";
    try {
      int newId = jdbcTemplate.queryForObject(
        sql,
        int.class,
        id,
        newProfile.getFirstName(),
        newProfile.getLastName(),
        newProfile.getEmail(),
        newProfile.getGoal()
      );
      profileToCreate = getProfileById(newId);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }

    return profileToCreate;
  }

  @Override
  public void updateProfile(int userId, UserProfile profileToUpdate) {
    UserProfile profile = null;
    String sql = "update user_profiles set first_name = ?, last_name = ?, email = ?, goal = ? where user_id = ? returning user_profile_id;";

    try {
      int newId = jdbcTemplate.queryForObject(sql, int.class, profileToUpdate.getFirstName(),
          profileToUpdate.getLastName(),
          profileToUpdate.getEmail(), profileToUpdate.getGoal(), userId);

      profile = getProfileById(newId);

      if (profile == null) {
        throw new DaoException("Unable to update profile");
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }

  }

  @Override
  public void deleteProfile(int userId) {
    String deleteUserProfileSQL = "delete from user_profiles where user_id = ?;";
    String deleteUserSQL = "delete from users where user_id = ?;";

    try {
      jdbcTemplate.update(deleteUserProfileSQL, userId);
      jdbcTemplate.update(deleteUserSQL, userId);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    }
  }

  private UserProfile mapRowToProfile(SqlRowSet results) {
    UserProfile profile = new UserProfile();
    profile.setProfileId(results.getInt("user_profile_id"));
    profile.setUserId(results.getInt("user_id"));
    profile.setFirstName(results.getString("first_name"));
    profile.setLastName(results.getString("last_name"));
    profile.setGoal(results.getString("goal"));
    profile.setEmail(results.getString("email"));
    profile.setProfilePicture("");
    return profile;
  }
}
