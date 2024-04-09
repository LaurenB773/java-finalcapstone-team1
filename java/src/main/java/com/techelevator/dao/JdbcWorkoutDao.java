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
    return null;
  }

  @Override
  public Workout updateWorkout(int id, Workout workoutToUpdate) {
    return null;
  }

  @Override
  public void deleteWorkout(int id) {

  }

  @Override
  public Workout createWorkout (Workout newWorkout) {
    return null;
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
