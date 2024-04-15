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
      "SELECT user_profiles.user_id, " +
              "   user_profiles.first_name, " +
              "   user_profiles.last_name, " +
              "   user_profiles.email, " +
              "   user_profiles.profile_picture, " +
              "   user_profiles.goal, " +
              "   user_profiles.user_profile_id," +
              "       latest_checkins.latest_checkin_time, " +
              "       latest_checkins.checkout_time " +
              "FROM user_profiles " +
              "JOIN ( " +
              "    SELECT checkins.user_id, " +
              "           latest_checkin_time, " +
              "           checkout_time " +
              "    FROM ( " +
              "        SELECT user_id, " +
              "        MAX(checkin_time) AS latest_checkin_time " +
              "        FROM checkins " +
              "        GROUP BY user_id " +
              "    ) AS latest_checkins " +
              "    JOIN checkins ON checkins.user_id = latest_checkins.user_id " +
              "                 AND checkins.checkin_time = latest_checkins.latest_checkin_time " +
              ") AS latest_checkins ON latest_checkins.user_id = user_profiles.user_id " +
              "WHERE user_profiles.user_id IN ( " +
              "    SELECT user_id " +
              "    FROM users " +
              "    WHERE role = 'ROLE_USER' " +
              ");";
    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
        UserProfile userProfile = mapRowToProfile(results);
        userProfile.setLatest_checkin(results.getTimestamp("latest_checkin_time").toLocalDateTime());
        if (results.getTimestamp("checkout_time") != null) {
          userProfile.setLatest_checkout(results.getTimestamp("checkout_time").toLocalDateTime());
        } else {
          userProfile.setLatest_checkout(null);
        }
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
  public List<UserProfile> getEmployees() {
    List<UserProfile> emp = new ArrayList<>();
    String sql = "select * from user_profiles where user_id in " +
            "(select user_id from users where role = 'ROLE_EMPLOYEE');";
    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
        UserProfile user = mapRowToProfile(results);
        emp.add(user);
      }
      return emp;
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }
  }

  @Override
  public List<Workout> getWorkouts(int userId) {
    String sql = "select * from workouts where user_id = ?;";
    List<Workout> workouts = new ArrayList<>();

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
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
    String sql =
      "update user_profiles set first_name = ?, last_name = ?, email = ?, goal = ? where user_id = ? returning user_profile_id;";

    try {
      int rowsAffected = jdbcTemplate.update(
        sql,
        profileToUpdate.getFirstName(),
        profileToUpdate.getLastName(),
        profileToUpdate.getEmail(),
        profileToUpdate.getGoal(),
        userId
      );

      if (rowsAffected != 1) {
        throw new DaoException("Unable to update Profile");
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
  }

  @Override
  public void deleteProfile(int userId) {
    String deleteUserProfileSQL =
      "delete from user_profiles where user_id = ?;";
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
