package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Exercise;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class JdbcExerciseDao implements ExerciseDao {

  private final JdbcTemplate jdbcTemplate;

  public JdbcExerciseDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Exercise createExercise(Exercise exercise) {
    String sql = "insert into exercises (exercise_id, equipment_id, exercise_name, exercise_duration_minutes, reps, weight)"
        + "values (?, ?, ?, ?, ?, ?);";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql,
          exercise.getExerciseId(), exercise.getEquipmentId(),
          exercise.getExerciseName(), exercise.getExerciseDurationMinutes(),
          exercise.getReps(), exercise.getWeight());

      if (results.next()) {
        return mapToRowExercise(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

    return null;
  }

  @Override
  public List<Exercise> getAllUserExercisesByWorkoutId(int workoutId) {
    String sql = "select * from exercises join workout_exercises on exercises.exercise_id = workout_exercises.exercise_id where workout_id = ?;";
    List<Exercise> exercises = new ArrayList<>();

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutId);

      while (results.next()) {
        exercises.add(mapToRowExercise(results));
      }

    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

    return exercises;
  }

  @Override
  public Exercise getExerciseById(int id) {
    return null;
  }

  private Exercise mapToRowExercise(SqlRowSet row) {
    Exercise exercise = new Exercise();

    exercise.setExerciseId(row.getInt("exercise_id"));
    exercise.setEquipmentId(row.getInt("equipment_id"));
    exercise.setExerciseName(row.getString("exercise_name"));
    exercise.setExerciseDurationMinutes(row.getInt("exercise_duration_minutes"));
    exercise.setReps(row.getInt("reps"));
    exercise.setWeight(row.getDouble("weight"));

    return exercise;
  }
}
