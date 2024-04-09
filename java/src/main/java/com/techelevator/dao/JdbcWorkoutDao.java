package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Workout;
import org.springframework.dao.DataIntegrityViolationException;
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
  // We need to be able to get a list of exercises in a workout

  @Override
  public List<Workout> getAllWorkouts() {
    List <Workout> allWorkouts = new ArrayList<>();
    String sql = "select * from workouts";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
        allWorkouts.add(mapToRowWorkout(results));
      }
    }catch (CannotGetJdbcConnectionException e){
      throw new DaoException(e.getMessage());
    }
    return allWorkouts;
  }

  @Override
  public Workout getWorkoutById(int id) {
    String sql = "select * from workouts where workout_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      if (results.next()) {
        return mapToRowWorkout(results);
      }
    }catch (CannotGetJdbcConnectionException e){
      throw new DaoException(e.getMessage());
    }
    return null;
  }
  @Override
  public Workout createWorkout (Workout newWorkout) {
    Workout workoutToCreate = null;
    String sql = "INSERT INTO workouts (workout_id, start_time, end_time, user_profile_id) " +
            "VALUES (?, ?, ?, ?) returning workout_id";
    try {
      int id = jdbcTemplate.queryForObject(sql, int.class, newWorkout.getStartTime(),
              newWorkout.getEndTime(), newWorkout.getProfileId());
      workoutToCreate = getWorkoutById(id);
    }
    catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }
    return workoutToCreate;
  }

  @Override
  public Workout updateWorkout(int id, Workout workoutToUpdate) {
    String sql = "UPDATE schedules SET workout_id = ?, start_time = ?, " +
            "end_time = ?, user_profile_id = ?;";
    try {
      int numberOfRowsAffected = jdbcTemplate.update(sql, workoutToUpdate.getWorkoutId(), workoutToUpdate.getStartTime(),
              workoutToUpdate.getEndTime(), workoutToUpdate.getProfileId());
      if (numberOfRowsAffected > 0) {
        return workoutToUpdate;
      } else {
        throw new DaoException("Cannot find workout");
      }
    }catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }

  }
  @Override
  public void deleteWorkout(int id) {

  }

  private Workout mapToRowWorkout(SqlRowSet results){
    Workout workout = new Workout();
    workout.setWorkoutId(results.getInt("workout_id"));
    workout.setProfileId(results.getInt("user_profile_id"));
    LocalDate startTime = results.getDate("start_time").toLocalDate();
    workout.setStartTime(startTime);
    LocalDate endTime = results.getDate("end_time").toLocalDate();
    workout.setEndTime(endTime);
//   exercise_id is meant to be a list of users?
    return workout;
  }
}
