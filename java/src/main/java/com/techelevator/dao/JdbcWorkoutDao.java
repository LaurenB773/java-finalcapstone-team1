package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Workout;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWorkoutDao implements WorkoutDao {

  private JdbcTemplate jdbcTemplate;

  public JdbcWorkoutDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Workout> getWorkouts() {
    String sql = "select * from workouts where user_id = ?";
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
  public Workout getCurrentWorkout(int userId) {
    String sql = "select * from workouts where end_time is null and user_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

      if (results.next()) {
        return mapRowToWorkout(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

    return null;
  }

  // ! doesnt work
  public Workout createWorkout(Workout newWorkout) {
    Workout workoutToCreate = null;
    String sql =
      "INSERT INTO workouts (workout_id, start_time, end_time, user_profile_id) " +
      "VALUES (?, ?, ?, ?) returning workout_id";
    try {
      int id = jdbcTemplate.queryForObject(
        sql,
        int.class,
        newWorkout.getStartTime(),
        newWorkout.getEndTime(),
        newWorkout.getUserId()
      );
      workoutToCreate = getCurrentWorkout(id);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }
    return workoutToCreate;
  }

  public Workout updateWorkout(int id, Workout workoutToUpdate) {
    String sql =
      "UPDATE schedules SET workout_id = ?, start_time = ?, " +
      "end_time = ?, user_profile_id = ?;";
    try {
      int numberOfRowsAffected = jdbcTemplate.update(
        sql,
        workoutToUpdate.getWorkoutId(),
        workoutToUpdate.getStartTime(),
        workoutToUpdate.getEndTime(),
        workoutToUpdate.getUserId()
      );
      if (numberOfRowsAffected > 0) {
        return workoutToUpdate;
      } else {
        throw new DaoException("Cannot find workout");
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }
  }

  public void startWorkout(int userId) {
    String sql = "insert into workouts (user_id, start_time) values (?, ?)";

    try {
      jdbcTemplate.update(sql, userId, LocalDateTime.now());
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
  }

  @Override
  public void endWorkout(int workoutId) {
    String sql = "update workouts set end_time = ? where workout_id = ?";

    try {
      jdbcTemplate.update(sql, LocalDateTime.now(), workoutId);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
  }

  protected static Workout mapRowToWorkout(SqlRowSet row) {
    Workout workout = new Workout();

    workout.setWorkoutId(row.getInt("workout_id"));
    workout.setUserId(row.getInt("user_id"));

    LocalDateTime startTime = row.getTimestamp("start_time").toLocalDateTime();
    workout.setStartTime(startTime);

    if (row.getTimestamp("end_time") != null) {
      LocalDateTime endTime = row.getTimestamp("end_time").toLocalDateTime();
      workout.setEndTime(endTime);
    }

    return workout;
  }
}
