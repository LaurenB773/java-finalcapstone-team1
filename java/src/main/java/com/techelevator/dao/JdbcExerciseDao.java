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
  public List<Exercise> getExercises(int userId) {
    List<Exercise> exercises = new ArrayList<>();
    String sql = "select * from exercises join user_exercises on exercises.exercise_id = user_exercises.exercise_id where user_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

      while (results.next()) {
        exercises.add(mapToRowExercise(results));
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to database");
    }

    return exercises;
  }

  @Override
  public Exercise getExerciseById(int exerciseId) {
    String sql = "select * from exercises where exercise_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, exerciseId);

      if (results.next()) {
        return mapToRowExercise(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to database");
    }

    return null;
  }

  @Override
  public Exercise createExercise(CreateExerciseDto dto, int userId) {
    String exerciseSql = "insert into exercises (exercise_name, exercise_duration_minutes, reps, sets, weight_lbs) values (?, ?, ?, ?, ?) returning exercise_id;";
    String userExerciseSql = "insert into user_exercises (user_id, exercise_id) values (?, ?);";
    String equipmentSql = "update equipments set used_time_minutes = used_time_minutes + ? where equipment_id = ?;";

    Exercise exercise = dto.getExercise();

    try {
      int exerciseId = jdbcTemplate.queryForObject(exerciseSql, Integer.class,
          exercise.getExerciseName(), exercise.getExerciseDurationMinutes(),
          exercise.getReps(), exercise.getSets(), exercise.getWeightLbs());

      if (exerciseId == 0) {
        throw new DaoException("Unable to create exercise");
      }

      jdbcTemplate.update(userExerciseSql, userId, exerciseId);

      // This will run into a performance issue (not a problem for this project)
      // if there are a lot of equipment ids for each exercise being logged at once by
      // multiple users because it will update the equipments table for each equipment id
      for (int equipmentId : dto.getEquipmentIds()) {
        jdbcTemplate.update(equipmentSql, exercise.getExerciseDurationMinutes(), equipmentId);
      }

      return getExerciseById(exerciseId);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
  }

  private Exercise mapToRowExercise(SqlRowSet row) {
    Exercise exercise = new Exercise();

    exercise.setExerciseId(row.getInt("exercise_id"));
    exercise.setExerciseName(row.getString("exercise_name"));
    exercise.setExerciseDurationMinutes(row.getInt("exercise_duration_minutes")); 
    exercise.setSets(row.getInt("sets"));
    exercise.setReps(row.getInt("reps"));
    exercise.setWeightLbs(row.getDouble("weight_lbs"));

    return exercise;
  }
}
