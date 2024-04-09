package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserProfileDao implements UserProfileDao {

  private final JdbcTemplate jdbcTemplate;

  public JdbcUserProfileDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public UserProfile getProfile(int userId) {
    String sql = "select * from user_profiles where user_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

      if (results.next()) {
        return mapRowToProfile(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    }

    return null;
  }

  @Override
  public List<UserProfile> getMembers() {
    List<UserProfile> listOfMembers = new ArrayList<>();
    String sql =
      "select * from user_profiles where user_id in " +
      "(select user_id from users where role = 'member')";
    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
        UserProfile userProfile = mapRowToProfile(results);
        listOfMembers.add(userProfile);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }
    return listOfMembers;
  }

  @Override
  public void updateProfile(int userId, UserProfile profileToUpdate) {
    String sql =
      "UPDATE user_profiles SET first_name = ?, last_name = ?, email = ?, " +
      " goal = ? WHERE user_id = ?";
    try {
      int rowsAffected = jdbcTemplate.update(
        sql,
        profileToUpdate.getFirstName(),
        profileToUpdate.getLastName(),
        profileToUpdate.getEmail(),
        profileToUpdate.getGoal(),
        userId
      );
      if (rowsAffected == 0) {
        throw new DaoException("Cannot find the user profile!");
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }
  }

  @Override
  public void deleteProfile(int userId) {
    String sql = "delete from user_profiles where user_profile_id = ?";

    try {
      int rowsAffected = jdbcTemplate.update(sql, userId);
      if (rowsAffected == 0) {
        throw new DaoException("Cannot find the user profile!");
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }
  }

  @Override
  public UserProfile createProfile(UserProfile newProfile, int id) {
    UserProfile profileToCreate = null;
    String sql =
      "insert into user_profiles (user_id,first_name, last_name, email, goal) values((select user_id from users where user_id = ?),?,?,?,?) returning user_profile_id; ";
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

      profileToCreate = getProfileById(newId);
      int newId = jdbcTemplate.queryForObject(
        sql,
        int.class,
        id,
        newProfile.getFirstName(),
        newProfile.getLastName(),
        newProfile.getEmail(),
        newProfile.getGoal()
      );
      profileToCreate = getProfile(newId);
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
    String sql =
      "update user_profiles set first_name = ?, last_name = ?, email = ?, goal = ? where user_id = ? returning user_profile_id;";

    try {
      int newId = jdbcTemplate.queryForObject(
        sql,
        int.class,
        profileToUpdate.getFirstName(),
        profileToUpdate.getLastName(),
        profileToUpdate.getEmail(),
        profileToUpdate.getGoal(),
        userId
      );

      profile = getProfile(newId);

      if (profile == null) {
        throw new DaoException("Unable to update profile");
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
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
