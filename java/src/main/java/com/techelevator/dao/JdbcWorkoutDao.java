package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Workout;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWorkoutDao implements WorkoutDao {

  private JdbcTemplate jdbcTemplate;

  public JdbcWorkoutDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Workout> getWorkouts() {
    String sql = "select * from workouts";
    List<Workout> workouts = new ArrayList<>();

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

      while (results.next()) {
        workouts.add(mapRowToWorkout(results));
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

    return workouts;
  }

  @Override
  public Workout getWorkoutById(int id) {
    String sql = "select * from workouts where workout_id = ?";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

      if (results.next()) {
        return mapRowToWorkout(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

    return null;
  }

  @Override
  public void startWorkout(int userProfileId, int workoutId) {
    String sql = "insert into workouts (user_profile_id, workout_id, start_time) values (?, ?, ?)";

    try {
      jdbcTemplate.update(sql, userProfileId, workoutId, LocalDate.now());
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
  }

  @Override
  public void endWorkout(int userProfileId, int workoutId) {
    String sql = "update workouts set end_time = ? where user_profile_id = ? and workout_id = ?";

    try {
      jdbcTemplate.update(sql, LocalDate.now(), userProfileId, workoutId);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

  }

  protected static Workout mapRowToWorkout(SqlRowSet row) {
    Workout workout = new Workout();

    workout.setWorkoutId(row.getInt("workout_id"));
    workout.setUserProfileId(row.getInt("user_profile_id"));
    workout.setExerciseId(row.getInt("exercise_id"));

    LocalDate startTime = row.getDate("start_time").toLocalDate();
    workout.setStartTime(startTime);

    LocalDate endTime = row.getDate("end_time").toLocalDate();
    workout.setEndTime(endTime);

    return workout;
  }
}
