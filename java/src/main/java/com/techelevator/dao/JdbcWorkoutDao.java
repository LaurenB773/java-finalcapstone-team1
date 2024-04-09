package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Workout;

@Component
public class JdbcWorkoutDao implements WorkoutDao {

  private JdbcTemplate jdbcTemplate;

  public JdbcWorkoutDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  protected static Workout mapRowToWorkout(SqlRowSet row) {
    Workout workout = new Workout();

    workout.setWorkoutId(row.getInt("workout_id"));
    workout.setUserProfileId(row.getInt("user_profile_id"));
    workout.setExerciseId(row.getInt("exercise_id"));
    workout.setStartTime(row.getDate("start_time").toLocalDate());
    workout.setEndTime(row.getDate("end_time").toLocalDate());

    return workout;
  }
}
